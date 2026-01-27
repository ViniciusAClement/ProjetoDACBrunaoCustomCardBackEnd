# 5ª Release — Autenticação e Permissões (Tasklist)

Este documento descreve as tarefas necessárias para implementar o fluxo completo de autenticação solicitado na 5ª release:

- Login e logout
- Utilização de token de autenticação
- Permissões/autorizações por papel (role)

O backend atual é um **Spring Boot 3 (Java 17)** com **Spring Data JPA**, sem Spring Security implementado.

---

## 1) Decisões iniciais (arquitetura)

- [ ] **Definir roles do sistema**
  - [ ] `ROLE_ADMIN` (entidade `Admin`)
  - [ ] `ROLE_CLIENT` (entidade `Client`)

- [ ] **Definir estratégia de “usuário autenticável” (login por email)**
  - [ ] Opção A (entrega rápida): autenticar por `email` buscando em `AdminRepository` e `ClientRepository`
  - [ ] Opção B (refatoração maior): criar uma entidade única `User`

- [ ] **Definir rotas públicas vs privadas**
  - [ ] Rotas públicas mínimas: `POST /auth/login`
  - [ ] Avaliar se cadastro fica público: `POST /clients` e/ou `POST /admins`
  - [ ] Restante privado (ex.: produtos/pedidos/carrinho)

- [ ] **Definir o que significa logout**
  - [ ] Logout “stateless”: front apaga o token (backend só responde OK)
  - [ ] Logout “real”: blacklist de tokens (revogação) com persistência

---

## 2) Dependências e configurações

- [ ] **Adicionar dependências no `pom.xml`**
  - [ ] `spring-boot-starter-security`
  - [ ] Biblioteca JWT (ex.: `io.jsonwebtoken:jjwt-*` ou equivalente)

- [ ] **Adicionar propriedades de JWT** (`application.yml`, `application-dev.yml`, `application-test.yml` se necessário)
  - [ ] `security.jwt.secret`
  - [ ] `security.jwt.expiration-minutes`
  - [ ] `security.jwt.issuer` (opcional)

- [ ] **Criar beans essenciais**
  - [ ] `PasswordEncoder` (`BCryptPasswordEncoder`)
  - [ ] `AuthenticationManager`

---

## 3) Modelo de autenticação (UserDetails)

- [ ] **Criar DTOs de autenticação**
  - [ ] `LoginRequestDTO` (email, password)
  - [ ] `LoginResponseDTO` (token, tipo Bearer, expiração, role, userId/email)

- [ ] **Criar `UserDetails` próprio** (ex.: `AuthUser`)
  - [ ] Campos mínimos: id, email, passwordHash, authorities

- [ ] **Criar `UserDetailsService`**
  - [ ] Carregar usuário por email (Admin ou Client)

- [ ] **Garantir armazenamento seguro de senha**
  - [ ] Ajustar `AdminService.create` para salvar `password` com BCrypt
  - [ ] Ajustar `ClientService.create` para salvar `password` com BCrypt
  - [ ] Validar impacto em dados pré-existentes (se houver)

---

## 4) Endpoint de login

- [ ] **Criar controller/resource de autenticação**
  - [ ] `POST /auth/login`

- [ ] **Autenticar credenciais**
  - [ ] Usar `AuthenticationManager`

- [ ] **Gerar token JWT**
  - [ ] `sub` = email
  - [ ] claims: `role(s)`, `userId`, `type` (ADMIN/CLIENT)
  - [ ] `iat` / `exp`

- [ ] **Retornar `LoginResponseDTO`**
  - [ ] Token + metadados essenciais

---

## 5) Validação do token (filtro JWT)

- [ ] **Criar serviço de JWT** (ex.: `JwtService`)
  - [ ] gerar token
  - [ ] validar token
  - [ ] extrair subject/claims/authorities

- [ ] **Criar filtro** `OncePerRequestFilter`
  - [ ] Ler header `Authorization: Bearer <token>`
  - [ ] Validar token
  - [ ] Popular `SecurityContext`

- [ ] **Criar `SecurityConfig`**
  - [ ] `sessionCreationPolicy(STATELESS)`
  - [ ] Desabilitar CSRF (API stateless)
  - [ ] CORS habilitado
  - [ ] Liberar `POST /auth/login`
  - [ ] Proteger demais endpoints

---

## 6) Permissões (autorizações por role)

- [ ] **Definir matriz de acesso (mínima) por endpoint**
  - [ ] ADMIN:
    - [ ] criar/editar/deletar `products`, `categories`, `cars`, `carbrands`
  - [ ] CLIENT autenticado:
    - [ ] criar/alterar carrinho (`marketcars`, `marketcaritems`)
    - [ ] criar pedido (`orders`)

- [ ] **Implementar enforcement**
  - [ ] Via `authorizeHttpRequests()` no `SecurityConfig`
  - [ ] E/ou via `@EnableMethodSecurity` + `@PreAuthorize(...)`

- [ ] **(Se cobrado) Regra de “dono”**
  - [ ] Client só pode acessar/alterar recursos vinculados ao seu próprio `clientId`

---

## 7) Logout

- [ ] **Implementar endpoint de logout**
  - [ ] `POST /auth/logout`

- [ ] **Escolher estratégia**
  - [ ] Simples: retornar 200 e orientar front a apagar o token
  - [ ] Completa: blacklist/revogação
    - [ ] adicionar `jti` no token
    - [ ] persistir tokens revogados (com expiração)
    - [ ] validar blacklist no filtro JWT

---

## 8) Tratamento de erro (401/403) e padronização

- [ ] **Configurar respostas corretas do Spring Security**
  - [ ] 401: `AuthenticationEntryPoint`
  - [ ] 403: `AccessDeniedHandler`

- [ ] **Padronizar “not found”**
  - [ ] Ajustar `GenericServices` para lançar `EntityNotFoundException` (evitar `RuntimeException` => 500)

---

## 9) Testes e validação (Postman)

- [ ] **Criar/atualizar coleção Postman**
  - [ ] Login -> salvar token em variável
  - [ ] Chamar endpoint protegido sem token -> 401
  - [ ] Chamar endpoint ADMIN com token CLIENT -> 403
  - [ ] Token expirado -> 401

- [ ] **Casos mínimos**
  - [ ] email inexistente
  - [ ] senha inválida
  - [ ] token malformado

---

## 10) Ajustes que podem bloquear a entrega

- [ ] **Corrigir inconsistências de rotas (docs x código)**
  - [ ] `AdressResource`: `/adresses` vs docs (`/addresses`)
  - [ ] `MarketCarItemResource`: `/marketcaritems` vs docs (`/marketcaritens`)

- [ ] **CORS**
  - [ ] Trocar método `UPDATE` por `PUT` (ou ajustar lista de métodos permitidos corretamente)

---

## Checklist final de entrega

- [ ] Login funcionando e retornando JWT
- [ ] Token validando e protegendo endpoints
- [ ] Permissões por role (401 vs 403 corretamente)
- [ ] Logout definido e implementado conforme decisão (simples ou blacklist)
- [ ] Postman atualizado para testar o fluxo completo

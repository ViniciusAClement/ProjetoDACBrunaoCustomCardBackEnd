# Checklist de melhorias — Services

Este documento lista recomendações práticas, prioridades e checklists para melhorar a qualidade, robustez e manutenção dos serviços (`src/main/java/com/bcc/cca/services`). Não serão feitas alterações no código — apenas sugestões e riscos a evitar.

**Como usar:** copie itens para tickets e aplique por prioridade (Alta → Média → Baixa).

**Prioridades**
- Alta: bugs críticos, segurança, corrupção de dados, crashes, concorrência.
- Média: refatorações que reduzem débito técnico e melhoram manutenção.
- Baixa: melhorias de estilo, documentação e otimizações não críticas.

---

**Sumário rápido (ações recomendadas)**
- Substituir usos de `Optional.get()` por `orElseThrow(...)` com exceções específicas. (Alta)
- Adicionar validação de entrada nos DTOs (`@Valid`, `@NotNull`, `@Size`). (Alta)
- Centralizar tratamento de exceções (ControllerAdvice) e usar exceções customizadas. (Alta)
- Evitar múltiplas chamadas ao repositório para o mesmo id; recuperar uma vez e reutilizar. (Média)
- Melhorar transações: revisar onde `@Transactional` é necessário e usar `readOnly=true` para consultas. (Média)
- Cobertura de testes unitários e integração para fluxos de criação e update. (Média)
- Evitar salvar a mesma entidade múltiplas vezes desnecessariamente; ajustar cascades. (Média)
- Evitar N+1: revisar fetch type e consultas para carregar listas por lote. (Média)
- Remover imports/duplicidades e pequenas melhorias de estilo (ex.: `CarService` imports duplicados). (Baixa)

---

**Problemas recorrentes observados (exemplos concretos)**

- Uso de `findById(id).get()` sem checagem — pode lançar `NoSuchElementException` e gerar 500 inesperado. Ex.: `ProductService`, `MarketCarItemService`, `OrderService`, `CardInfoService`, `AdressService`, `ClientService`.
- Chamadas repetidas ao mesmo repositório/id (ex.: `marketCarRepository.findById(dto.getMarketCarId()).get()` chamadas múltiplas vezes). Reuse a instância recuperada.
- Atualizações manuais de relações bi-direcionais sem checar cascades e persistência podem provocar inconsistências e salvar entidades em ordem errada.
- Falta de validação/constraints nos DTOs: dados inválidos podem chegar ao service e persistir inconsistências.
- Mensagens de erro genéricas (`RuntimeException("Registro não encontrado")`) — dificultam tratativa e mapeamento para respostas HTTP adequadas.
- Possível falta de testes cobrindo transações complexas (ex.: limpar carrinho após criar order em `OrderService`).

---

**Checklist detalhado — Melhorias por categoria**

**Validação e Resiliência (Alta)**
- [X] Substituir todos os `findById(...).get()` por `findById(...).orElseThrow(() -> new ResourceNotFoundException("X not found"))`.
- [x] Criar exceções customizadas: `ResourceNotFoundException`, `BadRequestException`, `ConflictException`.
- [x] Adicionar `@Valid` e constraints nos DTOs (ex.: `@NotNull`, `@NotEmpty`, `@Positive`) e habilitar validação nos controllers.
- [x] Implementar `@ControllerAdvice` para mapear exceções para respostas JSON padronizadas.

**Transações e Persistência (Alta/Média)**
- [X] Revisar uso de `@Transactional`: garantir que métodos que mutam estado estejam transacionais; usar `@Transactional(readOnly=true)` em buscas.
- [x] Evitar salvar a mesma entidade várias vezes: analisar `ProductService.create` que chama `repository.save(product)` duas vezes; geralmente basta salvar no final com cascades corretos.
- [x] Consolidar chamadas que buscam coleções por ids em uma única query (ex.: buscar todas as `Category` por ids com `findAllById`), evitar loop de `findById`.
- [X] Revisar `cascade` e `orphanRemoval` nas entidades para reduzir manual save/remove.

**Design e Responsabilidade (Média)**
- [X] Evitar lógica de negócio complexa no service que mistura várias responsabilidades — extrair validações/transformações para helpers ou outro layer quando necessário.
- [X] Padronizar retorno dos services: sempre DTOs; evitar retornar entidades JPA.
- [X] Centralizar mapeamentos entre DTO <-> Entity nos mappers (já presente) e garantir testes desses mappers.

**Performance e consultas (Média)**
- [ ] Mitigar N+1: revisar relações `@OneToMany` e `@ManyToOne` e usar `@EntityGraph` ou `join fetch` quando necessário.
- [ ] Implementar paginação para `findAll()` quando dataset pode crescer; substituir `getRepository().findAll()` por page requests.

**Testes e Qualidade (Média)**
- [ ] Cobertura de testes unitários para services: criar mocks de repositories e testar fluxos felizes e exceções.
- [ ] Testes de integração (Spring Boot Test) cobrindo transações, cascades e fluxos (ex.: criar pedido e validar carrinho limpo).

**Limpeza e estilo (Baixa)**
- [ ] Remover imports duplicados e não usados (ex.: `CarService` tem imports repetidos).
- [ ] Padronizar nomes de variáveis (ex.: `CarBrand CarBrand = ...` rename para `carBrand`).

---

**Checklist por Service (itens concretos detectados)**

- `ProductService`
  - [ ] Usar `categoryRepository.findAllById(dto.getCategoryIds())` e `carRepository.findAllById(dto.getCarIds())` para reduzir múltiplos `findById`.
  - [ ] Remover o primeiro `repository.save(product);` antes de estabelecer relações; persistir no final.
  - [ ] Tratar ausência de category/car com exceção amigável.

- `MarketCarItemService`
  - [ ] Evitar múltiplas chamadas `findById` para marketCar/product; recuperar uma vez.
  - [ ] Calcular e validar preço/quantidade antes de persistir; checar `product.getPrice()` não nulo.
  - [ ] Revisar transação: garantir que `productRepository.save(product)` e `marketCarRepository.save(marketCar)` sejam feitos atomically.

- `OrderService`
  - [ ] Trocar `clientRepository.findById(...).get()` por `orElseThrow`.
  - [ ] Considerar calcular `value` com `stream().mapToDouble(MarketCarItem::getPrice).sum()` (já parecido) e validar se o carrinho não está vazio.
  - [ ] Garantir que limpar `marketCar.getMarketCarItens().clear()` seja persistido (salvar marketCar) ou usar remoção via repository.

- `ClientService` / `AdressService` / `CardInfoService`
  - [ ] Usar `orElseThrow` em todas as buscas por id.
  - [ ] Garantir que relacionamentos (cliente ↔ cartão / endereço / marketcar) sejam sincronizados com cascades e salvos de maneira consistente.

- `CarService`
  - [ ] Corrigir imports duplicados.
  - [ ] Tratar `carBrandRepository.findById(...).get()` com `orElseThrow`.

- `GenericServices`
  - [ ] Substituir `RuntimeException("Registro não encontrado")` por `ResourceNotFoundException` com HTTP mapping.
  - [ ] Considerar implementar paginação no `findAll()` ou ao menos oferecer overloads com `Pageable`.

---

**Riscos futuros a evitar (lista curta)**
- Persistência parcial: salvar apenas parte do grafo de entidades e assumir cascade pode corromper dados.
- Concorrência: sem locks ou checagens, atualizações em massa podem sobrescrever dados (ex.: quantidade em `MarketCarItem`).
- Exposição de entidades JPA via JSON: pode causar lazy-loading exceptions ou revelar dados sensíveis; sempre retornar DTOs.

---

**Plano de ação sugerido (próximos passos técnicos)**
1. Alta prioridade (curto prazo, 1-2 dias): substituir `findById().get()` por `orElseThrow` + adicionar `ResourceNotFoundException` + `ControllerAdvice`.
2. Média prioridade (1-2 semanas): adicionar validações DTO + testes unitários básicos para services críticos (`OrderService`, `MarketCarItemService`, `ProductService`).
3. Evolutivo (2-4 semanas): refatorar pontos que fazem múltiplas buscas por id para usar `findAllById` em lote; revisar cascades e transações.

---

**Anexos / pontos de referência (onde olhar no código)**
- Serviços analisados: `src/main/java/com/bcc/cca/services` (todos os arquivos). Sugestões aplicam-se a arquivos como `ProductService`, `MarketCarItemService`, `OrderService`, `ClientService`, `CardInfoService`, `AdressService`, `CarService`, `GenericServices`.

---

**Se quiser eu posso:**
- gerar PRs pequenos e seguros que implementem somente as mudanças de maior prioridade (ex.: substituir `get()` por `orElseThrow` e adicionar `ResourceNotFoundException`), ou
- criar testes unitários exemplares para `OrderService` e `MarketCarItemService`.

Escolha qual ação prefere: aplicar correções de alta prioridade via PRs, ou gerar testes primeiro.

*** FIM ***

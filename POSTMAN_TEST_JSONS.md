# JSONs para Testes no Postman

## Endpoints e JSONs de Exemplo

### 1. Admin - POST /admins
```json
{
  "name": "João Silva",
  "email": "joao.silva@admin.com",
  "phone": "(11) 98765-4321",
  "password": "senha123",
  "cpf": "123.456.789-00"
}
```

### 2. User - POST /users
```json
{
  "name": "Maria Santos",
  "email": "maria.santos@email.com",
  "phone": "(11) 91234-5678",
  "password": "senha456",
  "cpf": "987.654.321-00"
}
```

### 3. Client - POST /clients
```json
{
  "name": "Pedro Oliveira",
  "email": "pedro.oliveira@email.com",
  "phone": "(11) 99876-5432",
  "password": "senha789",
  "cpf": "111.222.333-44"
}
```

### 4. CarBrand - POST /carbrands
```json
{
  "name": "Toyota"
}
```

### 5. Car - POST /cars
```json
{
  "name": "Corolla",
  "yearOfCar": 2023,
  "carBrand": {
    "id": 1
  }
}
```

### 6. Category - POST /categories
```json
{
  "name": "Peças de Motor"
}
```

### 7. Product - POST /products
```json
{
  "name": "Filtro de Óleo",
  "description": "Filtro de óleo original para veículos",
  "price": 45.90,
  "imgUrl": "https://example.com/images/filtro-oleo.jpg"
}
```

### 8. Adress - POST /addresses
```json
{
  "street": "Rua das Flores",
  "number": 123,
  "zipcode": "01234-567",
  "state": "SP",
  "city": "São Paulo",
  "client": {
    "id": 1
  }
}
```

### 9. CardInfo - POST /cardinfos
```json
{
  "cardNumber": "1234567890123456",
  "creditCardOwner": "PEDRO OLIVEIRA",
  "cardType": 1,
  "client": {
    "id": 1
  }
}
```
**Nota:** cardType: 1 = CREDIT, 2 = DEBIT

### 10. MarketCar - POST /marketcars
```json
{
  "client": {
    "id": 1
  }
}
```

### 11. MarketCarItem - POST /marketcaritens
```json
{
  "quantity": 2,
  "price": 45.90,
  "product": {
    "id": 1
  },
  "marketcar": {
    "id": 1
  }
}
```

### 12. Payment - POST /payments
```json
{
  "instant": "2024-01-15T10:30:00Z",
  "paymentMethod": 1
}
```
**Nota:** paymentMethod: 1 = CARD, 2 = PIX, 3 = TICKET

### 13. Order - POST /orders
```json
{
  "paymentStatus": 2,
  "deliveryStatus": 3,
  "instant": "2024-01-15T10:30:00Z",
  "client": {
    "id": 1
  },
  "payment": {
    "id": 1
  }
}
```
**Nota:** 
- paymentStatus: 1 = PAID, 2 = UNPAID, 3 = CANCELED
- deliveryStatus: 1 = SENDING, 2 = DELIVERED, 3 = PROCESSING

---

## Exemplos de UPDATE (PUT)

### Admin - PUT /admins/{id}
```json
{
  "name": "João Silva Atualizado",
  "email": "joao.silva.novo@admin.com",
  "phone": "(11) 98765-9999",
  "cpf": "123.456.789-00"
}
```

### Client - PUT /clients/{id}
```json
{
  "name": "Pedro Oliveira Atualizado",
  "email": "pedro.novo@email.com",
  "phone": "(11) 99876-0000",
  "cpf": "111.222.333-44"
}
```

### Product - PUT /products/{id}
```json
{
  "name": "Filtro de Óleo Premium",
  "description": "Filtro de óleo premium com maior durabilidade",
  "price": 65.90,
  "imgUrl": "https://example.com/images/filtro-oleo-premium.jpg"
}
```

### Car - PUT /cars/{id}
```json
{
  "name": "Corolla XEI",
  "yearOfCar": 2024,
  "carBrand": {
    "id": 1
  }
}
```

### Order - PUT /orders/{id}
```json
{
  "paymentStatus": 1,
  "deliveryStatus": 1,
  "instant": "2024-01-15T11:00:00Z",
  "client": {
    "id": 1
  },
  "payment": {
    "id": 1
  }
}
```

### MarketCarItem - PUT /marketcaritens/{id}
```json
{
  "quantity": 3,
  "price": 45.90,
  "product": {
    "id": 1
  }
}
```

---

## Ordem Recomendada para Criar Dados (Dependências)

1. **CarBrand** - Não tem dependências
2. **Category** - Não tem dependências
3. **Product** - Não tem dependências (pode ser criado sozinho)
4. **User/Admin/Client** - Não tem dependências
5. **Car** - Precisa de CarBrand
6. **Adress** - Precisa de Client
7. **CardInfo** - Precisa de Client
8. **MarketCar** - Precisa de Client
9. **MarketCarItem** - Precisa de Product e MarketCar
10. **Payment** - Não tem dependências
11. **Order** - Precisa de Client e Payment

---

## Exemplos de Relacionamentos ManyToMany

### Adicionar Product a Category (via código ou endpoint específico)
Nota: Os relacionamentos ManyToMany geralmente são gerenciados através de métodos específicos nas entidades ou endpoints dedicados.

### Adicionar Product a Car (via código ou endpoint específico)
Nota: Similar ao acima, pode precisar de endpoints específicos ou manipulação direta.

---

## Valores de Enum

### PaymentStatus
- `1` = PAID
- `2` = UNPAID
- `3` = CANCELED

### DeliveryStatus
- `1` = SENDING
- `2` = DELIVERED
- `3` = PROCESSING

### PaymentMethod
- `1` = CARD
- `2` = PIX
- `3` = TICKET

### CardType
- `1` = CREDIT
- `2` = DEBIT

---

## Notas Importantes

1. **IDs**: Ao criar relacionamentos, use os IDs retornados após criar as entidades relacionadas
2. **Datas**: Use formato ISO 8601 para campos Instant (ex: "2024-01-15T10:30:00Z")
3. **CPF**: Use formato brasileiro (ex: "123.456.789-00")
4. **Telefone**: Use formato brasileiro (ex: "(11) 98765-4321")
5. **Preços**: Use números decimais (ex: 45.90)
6. **Campos opcionais**: Alguns campos podem ser omitidos se forem opcionais


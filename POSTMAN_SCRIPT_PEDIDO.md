# Script para Criar Pedido com 2 Produtos

## Ordem de Execução no Postman

### 1. Criar Cliente
**POST** `/clients`
```json
{
  "name": "João Comprador",
  "email": "joao.comprador@email.com",
  "phone": "(11) 98765-4321",
  "password": "senha123",
  "cpf": "123.456.789-00"
}
```
**Anote o ID retornado** (exemplo: `id: 1`)

---

### 2. Criar Produto 1
**POST** `/products`
```json
{
  "name": "Filtro de Óleo Premium",
  "description": "Filtro de óleo original para veículos",
  "price": 45.90,
  "imgUrl": "https://example.com/images/filtro-oleo.jpg"
}
```
**Anote o ID retornado** (exemplo: `id: 1`)

---

### 3. Criar Produto 2
**POST** `/products`
```json
{
  "name": "Pastilha de Freio Original",
  "description": "Pastilha de freio premium",
  "price": 89.50,
  "imgUrl": "https://example.com/images/pastilha-freio.jpg"
}
```
**Anote o ID retornado** (exemplo: `id: 2`)

---

### 4. Criar Carrinho do Cliente
**POST** `/marketcars`
```json
{
  "client": {
    "id": 1
  }
}
```
**Use o ID do cliente do passo 1. Anote o ID do MarketCar retornado** (exemplo: `id: 1`)

---

### 5. Adicionar Produto 1 ao Carrinho (3 unidades)
**POST** `/marketcaritens`
```json
{
  "quantity": 3,
  "product": {
    "id": 1
  },
  "marketcar": {
    "id": 1
  }
}
```
**Use os IDs do produto 1 e do MarketCar criados anteriormente**

---

### 6. Adicionar Produto 2 ao Carrinho (5 unidades)
**POST** `/marketcaritens`
```json
{
  "quantity": 5,
  "product": {
    "id": 2
  },
  "marketcar": {
    "id": 1
  }
}
```
**Use os IDs do produto 2 e do MarketCar criados anteriormente**

---

### 7. Criar Pedido (calcula automaticamente o valor)
**POST** `/orders`
```json
{
  "client": {
    "id": 1
  },
  "payment": {
    "paymentMethod": 1
  },
  "paymentStatus": 2,
  "deliveryStatus": 3
}
```

**O sistema vai:**
- Buscar automaticamente os itens do carrinho do cliente
- Calcular o valor total: (45.90 × 3) + (89.50 × 5) = **585.20**
- Criar o Payment com esse valor
- Criar o Order associado

---

## Cálculo do Valor Total

- **Produto 1**: 45.90 × 3 = **137.70**
- **Produto 2**: 89.50 × 5 = **447.50**
- **TOTAL**: **585.20**

---

## Valores dos Enums

- **paymentMethod**: 1 = CARD, 2 = PIX, 3 = TICKET
- **paymentStatus**: 1 = PAID, 2 = UNPAID, 3 = CANCELED
- **deliveryStatus**: 1 = SENDING, 2 = DELIVERED, 3 = PROCESSING

---

## Resposta Esperada do Order

```json
{
  "id": 1,
  "paymentStatus": 2,
  "deliveryStatus": 3,
  "instant": "2024-01-15T10:30:00Z",
  "payment": {
    "id": 1,
    "instant": "2024-01-15T10:30:00Z",
    "paymentMethod": 1,
    "amount": 585.20
  }
}
```




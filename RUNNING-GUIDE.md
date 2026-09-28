# Running Guide

## Requirements
- Java 17+
- Maven 3.6+
- MySQL 8+
- IntelliJ IDEA / Eclipse / VS Code

## MySQL
Start MySQL first.

Default project credentials:
- username: `root`
- password: `root`

If your credentials are different, edit `application.properties` in `user-service`, `product-service`, and `order-service`.

## Start Services
Start in this order:
1. `eureka-server` -> `mvn spring-boot:run`
2. `user-service` -> `mvn spring-boot:run`
3. `product-service` -> `mvn spring-boot:run`
4. `order-service` -> `mvn spring-boot:run`
5. `api-gateway` -> `mvn spring-boot:run`

Eureka: `http://localhost:8761`

Gateway: `http://localhost:8080`

## Test JWT Flow
### 1. Login as Admin
POST `http://localhost:8080/users/login`

```json
{
  "email": "admin@shopsphere.com",
  "password": "admin123"
}
```

Copy the returned `token`.

### 2. Add Bearer Token
For protected requests use this header:

`Authorization: Bearer <your-token>`

### 3. Create Product
Use the admin token:

POST `http://localhost:8080/products`

```json
{
  "name": "Laptop",
  "price": 55000,
  "stock": 10
}
```

### 4. Register Normal User
POST `http://localhost:8080/users/register`

```json
{
  "name": "Rahul",
  "email": "rahul@example.com",
  "password": "rahul123"
}
```

### 5. Login as Normal User
POST `http://localhost:8080/users/login`

Use Rahul's email and password, then copy the JWT.

### 6. Create Order
Use Rahul's token:

POST `http://localhost:8080/orders`

```json
{
  "userId": 1,
  "productId": 1,
  "quantity": 2
}
```

Order Service forwards the same JWT to Product Service through Feign when it checks the product.

## Common Interview Points
- JWT is stateless, so the server does not need an HTTP session.
- Passwords are stored as BCrypt hashes, not plain text.
- `ADMIN` can create products.
- `USER` can place orders.
- The JWT contains the role, which Spring Security converts into `ROLE_USER` or `ROLE_ADMIN`.

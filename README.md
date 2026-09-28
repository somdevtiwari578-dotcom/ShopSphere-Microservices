# ShopSphere - Interview Oriented Microservices

A simple Java 17 + Spring Boot e-commerce microservices project designed for learning and interviews.

## Services
- Eureka Server: 8761
- API Gateway: 8080
- User Service: 8081
- Product Service: 8082
- Order Service: 8083

## Technologies
Java 17, Spring Boot 3.5.16, Spring Cloud 2025.0.0, Spring Web, Spring Security, JWT (JJWT), BCrypt, Spring Data JPA, MySQL, Eureka, API Gateway, OpenFeign, Maven, JUnit.

## Architecture
Client -> API Gateway -> Eureka -> User/Product/Order Services

Order Service -> OpenFeign -> Product Service

Each business service owns a separate MySQL database.

## Security Flow
1. User registers with `/users/register`.
2. User logs in using `/users/login`.
3. User Service verifies the BCrypt password.
4. User Service generates a signed JWT containing email and role.
5. Client sends `Authorization: Bearer <token>` with protected requests.
6. Product and Order Services validate the JWT before allowing the request.
7. Product creation is restricted to `ADMIN`; orders require authentication.

## Demo Accounts
### Admin
- Email: `admin@shopsphere.com`
- Password: `admin123`
- Role: `ADMIN`

The admin account is automatically created when User Service starts.

### Normal User
Register your own user using `/users/register` with a password. New registrations always receive the `USER` role.

## Run
1. Start MySQL.
2. Start `eureka-server`.
3. Start `user-service`.
4. Start `product-service`.
5. Start `order-service`.
6. Start `api-gateway`.
7. Import `postman/ShopSphere-Microservices.postman_collection.json`.

Default MySQL credentials are `root/root`. Change them in each service's `application.properties` if required.

## Important APIs
### Authentication
POST `/users/register`
```json
{"name":"Rahul","email":"rahul@example.com","password":"rahul123"}
```

POST `/users/login`
```json
{"email":"rahul@example.com","password":"rahul123"}
```

The response contains a JWT token. Copy it into the Postman `token` variable or send it manually as a Bearer token.

### Products
POST `/products` - ADMIN only
```json
{"name":"Laptop","price":55000,"stock":10}
```

GET `/products` - authenticated user
GET `/products/{id}` - authenticated user

### Orders
POST `/orders` - authenticated user
```json
{"userId":1,"productId":1,"quantity":2}
```

GET `/orders` - authenticated user
GET `/orders/{id}` - authenticated user

Use Gateway at `http://localhost:8080`.

## Interview Explanation
- **Spring Boot:** creates independent microservices quickly.
- **Eureka:** service discovery; services find each other by name.
- **API Gateway:** one entry point for the client.
- **JWT:** stateless authentication; the token carries the user's identity and role.
- **Spring Security:** protects APIs and checks roles.
- **BCrypt:** hashes passwords before storing them.
- **OpenFeign:** Order Service calls Product Service without manually writing HTTP client code.
- **JPA:** Repository methods handle basic database operations without writing SQL.
- **Separate databases:** each business service owns its own data.

The project intentionally avoids Kafka, Redis, Kubernetes and other advanced infrastructure so the complete code stays easy to explain in an interview.

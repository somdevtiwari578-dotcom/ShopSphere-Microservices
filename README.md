# ShopSphere – E-Commerce Microservices

ShopSphere is a backend-focused E-Commerce application built using a Microservices Architecture.

The project is developed using Java and Spring Boot and demonstrates practical implementation of service discovery, API Gateway, JWT authentication, role-based authorization, inter-service communication, REST APIs, and MySQL database integration.

---

## 🚀 Features

- User Registration
- User Login
- JWT Authentication
- Role-Based Authorization
- Admin Authentication
- Product Management
- Order Management
- Service Discovery using Eureka
- API Gateway
- Inter-Service Communication using OpenFeign
- Password Encryption using BCrypt
- MySQL Database Integration
- RESTful APIs
- Bean Validation
- Postman API Testing

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Spring Boot | Backend Development |
| Spring Cloud | Microservices Infrastructure |
| Spring Cloud Netflix Eureka | Service Discovery |
| Spring Cloud Gateway | API Gateway |
| Spring Security | Application Security |
| JWT | Authentication |
| BCrypt | Password Encryption |
| Spring Data JPA | Database Operations |
| OpenFeign | Microservice Communication |
| MySQL | Database |
| Maven | Build and Dependency Management |
| Postman | API Testing |
| IntelliJ IDEA | Development Environment |

---

# 🏗️ Microservices Architecture

The project is divided into independent microservices.

```text
                         Client / Postman
                                |
                                v
                       +------------------+
                       |   API Gateway    |
                       |      :8080       |
                       +--------+---------+
                                |
             +------------------+------------------+
             |                  |                  |
             v                  v                  v
     +---------------+  +---------------+  +---------------+
     | User Service  |  |Product Service|  | Order Service |
     |     :8081     |  |     :8082     |  |     :8083     |
     +-------+-------+  +-------+-------+  +-------+-------+
             |                  |                  |
             v                  v                  v
       MySQL Database     MySQL Database     MySQL Database

                    +----------------------+
                    |    Eureka Server     |
                    |        :8761         |
                    +----------------------+
📦 Microservices
1. Eureka Server

Port: 8761

Eureka Server acts as the Service Registry of the application.

All microservices register themselves with Eureka. It allows services to discover each other without using hard-coded service addresses.

Responsibilities
Service Registration
Service Discovery
Monitoring registered services

Eureka Dashboard:

http://localhost:8761
2. API Gateway

Port: 8080

API Gateway acts as the single entry point for client requests.

Instead of directly communicating with individual services, clients can send requests through the API Gateway.

Responsibilities
Request Routing
Single Entry Point
Communication with Microservices
Integration with Eureka Service Discovery

Example:

Client
   |
   v
API Gateway :8080
   |
   +----> User Service :8081
   |
   +----> Product Service :8082
   |
   +----> Order Service :8083
3. User Service

Port: 8081

User Service is responsible for user management and authentication.

Features
User Registration
User Login
Password Encryption
JWT Token Generation
User Roles
Authentication
User Roles

The application supports:

USER
ADMIN
Default Admin Account
Email: admin@shopsphere.com
Password: admin123
Role: ADMIN
4. Product Service

Port: 8082

Product Service manages products available in the e-commerce application.

Features
Create Product
Get Products
Product Information Management
Admin-based Product Creation
Example Product
{
  "name": "Laptop",
  "description": "Gaming Laptop",
  "price": 55000
}
5. Order Service

Port: 8083

Order Service manages customer orders.

Features
Create Order
Get Orders
Calculate Total Price
Store Order Information
Communication with User and Product Services
Example Order Request
{
  "userId": 2,
  "productId": 1,
  "quantity": 2
}

If the product price is:

55000

and quantity is:

2

Then:

55000 × 2 = 110000

The order is stored in MySQL.

Example database record:

+----+------------+----------+-------------+---------+
| id | product_id | quantity | total_price | user_id |
+----+------------+----------+-------------+---------+
|  1 |          1 |        2 |      110000 |       2 |
+----+------------+----------+-------------+---------+
🔐 Authentication and Security

ShopSphere uses Spring Security and JWT for authentication.

Authentication Flow
User
  |
  v
Login
  |
  v
User Service
  |
  v
Validate Email and Password
  |
  v
Generate JWT
  |
  v
Return JWT Token
  |
  v
Client sends JWT with requests
  |
  v
JWT Validation
  |
  v
Authenticated Request

JWT is sent using the Authorization header:

Authorization: Bearer <JWT_TOKEN>

Passwords are never stored as plain text.

BCrypt is used for password hashing and secure password storage.

🔄 Microservice Communication

ShopSphere uses OpenFeign for communication between microservices.

For example, when creating an order, the Order Service can communicate with other services to retrieve required information.

                 Order Service
                      |
             +--------+--------+
             |                 |
             v                 v
       User Service      Product Service
          :8081               :8082

OpenFeign provides a simple way to call another microservice using Java interfaces.

🗄️ Database Architecture

MySQL is used as the database.

Each major microservice has its own database.

shopsphere_users
shopsphere_products
shopsphere_orders
Users Database

Stores:

User ID
Name
Email
Password
Role
Products Database

Stores:

Product ID
Name
Description
Price
Orders Database

Stores:

Order ID
User ID
Product ID
Quantity
Total Price
📁 Project Structure
ShopSphere-Microservices/
│
├── eureka-server/
│   └── Service Discovery
│
├── api-gateway/
│   └── API Gateway
│
├── user-service/
│   └── User Management & Authentication
│
├── product-service/
│   └── Product Management
│
├── order-service/
│   └── Order Management
│
├── postman/
│   └── Postman API Collection
│
├── ARCHITECTURE.md
├── RUNNING-GUIDE.md
├── README.md
└── .gitignore
⚙️ Prerequisites

Before running the project, make sure the following are installed:

Java 17 or higher
Maven
MySQL
IntelliJ IDEA
Postman
🗃️ Database Setup

Open MySQL and create the required databases:

CREATE DATABASE shopsphere_users;

CREATE DATABASE shopsphere_products;

CREATE DATABASE shopsphere_orders;

The application will create the required tables automatically through JPA/Hibernate configuration.

▶️ How to Run the Project

Start the services in the following order.

Step 1 – Start Eureka Server

Run:

eureka-server

Port:

8761

Open:

http://localhost:8761
Step 2 – Start User Service

Run:

user-service

Port:

8081
Step 3 – Start Product Service

Run:

product-service

Port:

8082
Step 4 – Start Order Service

Run:

order-service

Port:

8083
Step 5 – Start API Gateway

Run:

api-gateway

Port:

8080

After starting all services, Eureka Dashboard should show the registered services.

🧪 Postman Testing

A Postman collection is included in the project.

Location:

postman/ShopSphere-Microservices.postman_collection.json

The collection contains requests for:

Register User
Login User
Login Admin
Get Products
Create Product
Create Order
Get Orders
🔗 Important API Endpoints
User APIs
Register User
POST /users/register

Example:

{
  "name": "Rahul",
  "email": "rahul@example.com",
  "password": "rahul123"
}
Login
POST /users/login
Product APIs
Get Products
GET /products
Create Product
POST /products

Admin authentication is required.

Example:

{
  "name": "Laptop",
  "description": "Gaming Laptop",
  "price": 55000
}
Order APIs
Create Order
POST /orders

Example:

{
  "userId": 2,
  "productId": 1,
  "quantity": 2
}
Get Orders
GET /orders
🔄 Complete Request Flow

Example: Creating an order

User
 |
 | Login
 v
User Service
 |
 | JWT Token
 v
Client / Postman
 |
 | POST /orders + JWT
 v
API Gateway
 |
 v
Order Service
 |
 +------> User Service
 |
 +------> Product Service
 |
 v
Calculate Total Price
 |
 v
Save Order
 |
 v
MySQL
🧠 Key Concepts Demonstrated

This project demonstrates practical implementation of:

Microservices Architecture
Service Discovery
API Gateway
REST APIs
JWT Authentication
Spring Security
Role-Based Authorization
BCrypt Password Hashing
OpenFeign
Spring Data JPA
MySQL
Entity Mapping
Bean Validation
Exception Handling
Maven
Postman API Testing
🎯 Project Objective

The main objective of ShopSphere is to demonstrate how an e-commerce backend can be developed using a distributed microservices architecture.

The application separates major business responsibilities into independent services:

User Management
       |
       v
User Service

Product Management
       |
       v
Product Service

Order Management
       |
       v
Order Service

This separation makes the application easier to maintain and allows individual services to be developed and scaled independently.

🚀 Future Improvements

The following features can be added in future versions:

Docker and Docker Compose
Payment Service
Notification Service
Redis Caching
Kafka / RabbitMQ
Centralized Configuration
Resilience4j Circuit Breaker
Distributed Tracing
Cloud Deployment
CI/CD Pipeline
📌 Project Status

Completed and tested locally.

The following components have been successfully implemented and tested:

User Registration
User Login
JWT Authentication
Admin Authentication
Product Creation
Product Retrieval
Order Creation
MySQL Database Integration
Eureka Service Discovery
API Gateway
OpenFeign Communication
Postman API Testing
👨‍💻 Author

Somdev Tiwari

GitHub:

https://github.com/somdevtiwari578-dotcom

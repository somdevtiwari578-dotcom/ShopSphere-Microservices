# Architecture

```text
Client / Postman
       |
       v
 API Gateway :8080
       |
       v
 Eureka Server :8761
   /      |       \
  v       v        v
User    Product    Order
:8081   :8082     :8083
  |       |         |
MySQL   MySQL     MySQL
                  |
                  | OpenFeign
                  v
              Product Service
```

Order flow:
1. Request reaches Gateway.
2. Gateway finds Order Service using Eureka.
3. Order Controller receives JSON.
4. Order Service calls Product Service through Feign.
5. Product price/stock is returned.
6. Total price is calculated.
7. Order is saved.

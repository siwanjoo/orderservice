# orderservice

Spring Boot based order service practice project.

## Goals
- Practice domain modeling with Spring Data JPA
- Build REST APIs for product and order flows
- Add Redis caching and idempotency patterns
- Use Kafka for async event processing
- Run local infra with Docker Compose
- Prepare for Kubernetes deployment practice

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Redis
- Kafka
- Docker / Docker Compose
- Kubernetes (later)
- Swagger/OpenAPI
- JUnit5
- Testcontainers

## Current Scope
- Domain entities:
  - Product(id, name, price, stock)
  - Order(id, orderStatus, orderedAt, totalPrice)
  - OrderItem(id, order, product, quantity, orderPrice)
- Relationships:
  - Order 1:N OrderItem
  - OrderItem N:1 Product
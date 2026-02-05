# Price Checker API

This project is a Spring Boot REST API built following Hexagonal Architecture principles.
It provides an endpoint to retrieve the applicable price for a product based on date, brand and product identifier.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Swagger / OpenAPI
- Maven
- Docker

---

## Architecture

The application follows a Hexagonal Architecture approach:

- **Domain**: Business logic and rules.
- **Application**: Use cases and orchestration.
- **Infrastructure**: REST controllers, persistence, configuration.
- **Common**: Shared utilities and cross-cutting concerns.

---

## API Endpoint

### GET /prices

**Query Parameters**
- `applicationDate` (ISO-8601 datetime)
- `productId` (Long)
- `brandId` (Long)

**Response**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45
}
```

---

## Swagger UI

After starting the application, Swagger UI is available at:

``http://localhost:8080/swagger-ui.html``

---

## Running the Application
### Run locally
``mvn clean spring-boot:run``

### Run tests
``mvn clean test``

### Run with Docker
``docker build -t price-checker .``

``docker run -p 8080:8080 price-checker``

---

## Database
The application uses an in-memory H2 database, initialized with sample data at startup.

---

## Notes
- Business rules are fully isolated from framework dependencies.
- Input validation and error handling follow REST best practices.
- End-to-end tests cover all required scenarios from the technical test.
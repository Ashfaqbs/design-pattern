# dp-springboot — Design Patterns in Spring Boot

## Purpose
Real-world Spring Boot implementations of design patterns. Each pattern lives in its own
package under `src/main/java/com/patterns/dp_springboot/` and exposes REST endpoints so
the pattern can be exercised via HTTP calls.

## Tech Stack
- Java 17 + Spring Boot 3.3.5
- H2 in-memory database (JPA / Hibernate)
- Lombok (no boilerplate)
- Bean Validation (Jakarta)
- Spring Actuator (`/actuator/health`)

## How to Run
```bash
mvn spring-boot:run
# App starts on http://localhost:8080
# H2 console: http://localhost:8080/h2-console  (JDBC URL: jdbc:h2:mem:dp_db)
```

## How to Test
```bash
mvn test
```

## Project Structure
```
src/main/java/com/patterns/dp_springboot/
  DpSpringbootApplication.java     # Entry point
  <pattern>/                       # One package per design-pattern use case
    domain/                        # Entities, value objects
    repository/                    # Spring Data repositories
    service/                       # Business logic (pattern lives here)
    controller/                    # REST endpoints (thin)
    dto/                           # Request / Response records
    exception/                     # Custom exceptions
```

## Pattern Catalogue (add as built)
| Pattern | Package | Real-world scenario |
|---------|---------|---------------------|
| (next)  | TBD     | TBD                 |

## Key Decisions
- H2 keeps the app self-contained — no external DB needed.
- Each pattern is isolated in its own package; no cross-pattern dependencies.
- `ddl-auto=create-drop` so schema is always fresh on restart.

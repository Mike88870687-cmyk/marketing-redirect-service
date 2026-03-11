# Marketing Redirect Service

A backend service for managing short redirect links and tracking user clicks.

The service allows creating redirect links, tracking click events, and collecting basic analytics data.  
This project simulates the core functionality used in marketing and advertising platforms.

---

## Features

- Create short redirect links
- Redirect users via `/r/{code}`
- Track click events
- Store analytics data
- Basic link management API
- Pixel tracking endpoint (planned)
- REST API architecture

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Lombok

Planned:

- Swagger/OpenAPI
- Docker
- Analytics endpoints

---

## Project Structure

src/main/java/com/mike/redirect

controller      REST controllers
service         Business logic
repository      Database access layer
model           JPA entities
dto             API DTO objects
exception       Global error handling
config          Configuration classes

---

## Database Entities

### Link

Represents a redirect link.

Fields:

- id
- code
- destinationUrl
- createdAt

Example:

/r/abc123 → https://example.com

### Click

Represents a recorded click event.

Fields:

- id
- ip
- userAgent
- referer
- timestamp
- link_id

---

## API Endpoints

### Redirect

GET /r/{code}

Redirects user to the destination URL.

Example:

/r/test → https://google.com

---

## Running the Project

### Requirements

- Java 21
- Maven
- MySQL

---

### Run application

./mvnw spring-boot:run

Server starts on:

http://localhost:8080

---

## Development Workflow

Issue
↓
Code
↓
Commit
↓
Close Issue
↓
Lesson learned

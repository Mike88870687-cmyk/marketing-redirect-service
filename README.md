# Marketing Redirect Service

Spring Boot REST for managing marketing redirect links and tracking clicks.

## Feautures

- Create short redirect links
- Redirect users to target URLs
- Track redirect usage
- REST API for managing links

## Tech Stack
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

## Project Structure

src/main/java/com/mike/redirect

controller - REST controllers
service - business logic
repository - database access
model - JPA entities
dto - request/response objects
config - configuration
exception - error handling

## Running the Project

Clone repository:

git clone 
https://github.com/Mike88870687-cmyk/marketing-redirect-service.git

Run application:
./mvnw spring-boot:run 

Application will start on:
http://localhost:8080

## Future Improvements

- Click tracking analytics
- Admin dashboard
- Swagger documentation
- Docker support

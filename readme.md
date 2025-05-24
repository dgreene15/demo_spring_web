
## Goals
* Spring Boot Web Application 
  * WebFlux
* Uses Mockito for testing
* Has Swagger
* Custom Prometheus metric

## Setup
* Spring Boot 3.0.4
* Swagger 3 (openapi)
* Property file is local, outside of source control

## Access
* http://localhost:8080/employees/1
* https://jsonplaceholder.typicode.com/posts/1
* http://localhost:8080/v3/api-docs
* http://localhost:8080/swagger-ui/index.html
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator
* http://localhost:8080/actuator/prometheus

## Notes
* Create Wrapper (mvnw): mvn -N wrapper:wrapper


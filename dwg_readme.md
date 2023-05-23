
## Goals
* Spring webflux application that calls REST APIs.  
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

## Next Steps

* Mongodb create crud operators for article
* Continue to merge other github project content over so can delete the other GitHub project
* Create various test classes to demonstrate concepts.
* Experiment with two webclient calls where either can fail.  Check on doOnError behaviour.
* Try a test with webclient with subscribe to see if it makes the call with example from site below:
  * https://www.concretepage.com/spring-5/spring-webflux-controller
* Tutorials
* https://www.baeldung.com/start-here
* https://www.baeldung.com/get-started-with-java-series



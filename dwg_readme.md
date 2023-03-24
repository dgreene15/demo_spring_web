
## Goals
* Spring webflux application that calls REST APIs.  
* Uses Mockito for testing
* Has Swagger
* Custom Prometheus metric

## Setup
* http://localhost:8080/employees/1
* https://jsonplaceholder.typicode.com/posts/1

## Next Steps
* Create swagger documentation
* Add actuator and metrics
* Add version page for app
* Rewrite getPostById to handle missing id (use Optional); currently service gives 404 and we give 500
* Experiment with two webclient calls where either can fail.  Check on doOnError behaviour.
* Experiment with two webclient calls and use zip.
* Add a custom prometheus metric
* Try a test with webclient with subscribe to see if it makes the call with example from site below:
  * https://www.concretepage.com/spring-5/spring-webflux-controller

## Status
* 3/24/23 - Added test class for WebClient calls.
* 3/22/23 - Added test class for controller and service
* 3/19/23 - Initial setup of application.  Started working on controller.

## Goals
* Create spring webflux application that call REST api.  Uses Mockito for testing

## Setup
* http://localhost:8080/employees/1
* https://jsonplaceholder.typicode.com/posts/1

## Next Steps
* Switch employee domain object to "posts"
* Create adapter that calls webclient
* Try a test with webclient with subscribe to see if it makes the call with example from site below:
  * https://www.concretepage.com/spring-5/spring-webflux-controller
* Switch the webclient builder so it is a bean (created once)
* Update controller service so creates new "post" and returns it
* Build mockito test cases
* Create swagger documentation

## Status
* 3/19/23 - Initial setup of application.  Started working on controller.
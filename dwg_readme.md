
## Goals
* Create spring webflux application that call REST api.  Uses Mockito for testing

## Setup
* http://localhost:8080/employees/1
* https://jsonplaceholder.typicode.com/posts/1

## Next Steps
* Switch employee domain object to "posts"
* Create adapter that calls webclient
* Switch the webclient builder so it is a bean (created once)
* Update controller service so creates new "post" and returns it
* Build mockito test cases
* Create swagger documentation

## Status
* 3/19/23 - Initial setup of application.  Started working on controller.
# SurvHey BackEnd

The Spring Boot Server of the awesome SurvHey Project. 

## Overview 
The OpenAPI Specification is the openapi.yaml document in the root directory and is aditionally generated during runtime (localhost:8080/v2/api-docs).

## Run server
Start the server by running the deployment jar-file in the target directory:
```shell
java -jar swagger-spring-1.0.0.jar
```

## Run in Docker
```sh
docker build -t survhey-backend .
docker run -d --name survhey-backend -p 8080:8080 survhey-backend
```

The API is reachable under localhost:8080.
The BackEnd is included in the multi-container Docker stack in the [SurvHey-Delivery](https://git.dhbw-stuttgart.de/survhey/survhey-delivery) repository.

## Run tests

Run the tests through maven from the root directory:
```shell
mvn test
```

# SurvHey BackEnd

The Spring Boot Server of the awesome SurvHey Project. 

## Overview  
The OpenAPI Specification is the openapi.yaml document in the root directory and is aditionally generated during runtime (localhost:8080/v2/api-docs).

Start the server by running the deployment jar-file in the target directory:
```shell
java -jar swagger-spring-1.0.0.jar
```

Run the tests through maven from the root directory:
```shell
mvn test
```

The BackeEnd is included in the multi-container Docker stack in the [SurvHey-Delivery](https://git.dhbw-stuttgart.de/survhey/survhey-delivery) repository.

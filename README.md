# monty-hall

This project simulates the monty hall problem a certain number of times and records the result. The number of times the problem will be simulated is configured to 10000 by default, but can be changed in `application.yml`. The output in the console will display the results from the simulated games.

## Build project

```
./mvnw clean install
```

## Run jar-file

```
java -jar target/monty-hall-0.0.1-SNAPSHOT.jar
```

### Details
- Java version: 11
- Spring Boot version: 2.2.1
- Tests: Junit5 and Mockito

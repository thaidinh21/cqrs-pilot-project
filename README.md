# CQRS pilot
## Project for demostatrion CQRS service

### A service should contain the following base functionalities
- Kubernetes manifests
- Docker files
- Logging
- Healthcheck routes
- Swagger documentation
- response serialization.

## Features

- Create a functionality to create and read an <entity>. Follow CQRS pattern and event sourcing to propagate changes across read and write databases. Use mongodb as the database for both create and read service.


## Tech

Dillinger uses a number of open source projects to work properly:

- [Spring boot] - Java framework for create service
- [Mongodb] - NoSql storage
- [Redis] - Data structures server.
- [OpenApi] - For swagger documentation
- [Docker] - Container environment
- [spring-actuator] - Health checking
- [docker-compose] - Container environment
- [Kubenetes] -  A system for managing containerized applications


## Installation

CQRS requires:
- [Java](https://www.oracle.com/java/technologies/downloads/) jdk8+ to run in local
- [Docker] for run in container environment
- [Mongodb] mongo database
- [Redis] redis server


Install the dependencies and start the command service.

```sh
cd cqrs-service/command-service
mvn -T 1C clean install package -DskipTests
java -jar /target/command-service.0.0.1.jar
```

Use with docker environments...
```sh
cd cqrs-service
docker build -f command.Dockerfile -t command-service .
docker run -p 8082:8082 command-service
```
Use with docker-compose
``` sh
cd cqrs-service
docker-compose -f docker-compose.yml up --build 
```
Use with kubenetes
``` sh
cd cqrs-service
kubectl apply -f k8s
```

Verify the deployment by navigating to your server address in
your preferred browser.

```sh
http://127.0.0.1:8082/actuator/health
```
Api specification will be located at

```sh
http://127.0.0.1:8081/swagger-ui.html
```

For query service it is the same as command service
## License

MIT

[Spring Boot]: <https://github.com/spring-projects/spring-boot>
[Mongodb]: <https://github.com/mongodb/mongo>
[Redis]: <https://github.com/redis/redis>
[OpenApi]:<https://github.com/springdoc/springdoc-openapi>
[Docker]: <https://github.com/docker>
[docker-compose]: https://github.com/docker/compose
[Kubenetes]: <https://github.com/kubernetes/kubernetes>
[spring-actuator]: <https://github.com/spring-projects/spring-boot/tree/main/spring-boot-project/spring-boot-actuator>
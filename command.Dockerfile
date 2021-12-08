FROM openjdk:11-slim as builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn


COPY shared/pom.xml shared/pom.xml
COPY command-service/pom.xml command-service/pom.xml

COPY command.pom.xml ./pom.xml
RUN ./mvnw dependency:go-offline -B

COPY shared/src /app/shared/src
COPY command-service/src /app/command-service/src

RUN ./mvnw package -DskipTests

FROM openjdk:11-slim
COPY --from=builder /app/command-service/target/*.jar main.jar
ENTRYPOINT ["java","-jar","main.jar"]
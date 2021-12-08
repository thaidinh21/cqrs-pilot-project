FROM openjdk:11-slim as builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn


COPY shared/pom.xml shared/pom.xml
COPY query-service/pom.xml query-service/pom.xml

COPY query.pom.xml ./pom.xml
RUN ./mvnw dependency:go-offline -B

COPY shared/src /app/shared/src
COPY query-service/src /app/query-service/src

RUN ./mvnw package -DskipTests

FROM openjdk:11-slim
COPY --from=builder /app/query-service/target/*.jar main.jar
ENTRYPOINT ["java","-jar","main.jar"]
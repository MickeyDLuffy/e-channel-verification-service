FROM maven:3.8.5-openjdk-17-slim

WORKDIR /gateway-service

COPY ./pom.xml .

COPY ./src ./src

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "./target/gateway-service.jar"]
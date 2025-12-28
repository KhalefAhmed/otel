FROM eclipse-temurin:21-jre
LABEL authors="ahmed.khalef"
WORKDIR /app
COPY ./target/orders-service-0.0.1-SNAPSHOT.jar /app/app.jar
COPY ./opentelemetry/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
CMD ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "/app/app.jar"]
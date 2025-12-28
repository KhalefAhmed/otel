# Observability with OpenTelemetry: Two Approaches

This project demonstrates two ways to integrate observability with OpenTelemetry in Java Spring Boot microservices:

- **orders-service**: Java agent instrumentation
- **delivery-service**: Direct dependency instrumentation

## 1. orders-service: Java Agent Instrumentation

Instrumentation is done without modifying the source code. The OpenTelemetry Java agent is used, provided in the `opentelemetry/` folder.

### Main Steps:
1. Place the agent jar (`opentelemetry-javaagent.jar`) in the `orders-service/opentelemetry/` folder.
2. Add a configuration file `otel-agent.properties` to customize trace export.
3. Run the application with the following VM option:
   ```
   -javaagent:/path/to/opentelemetry-javaagent.jar -Dotel.javaagent.configuration-file=/path/to/otel-agent.properties
   ```
   Example for this project:
   ```
   -javaagent:orders-service/opentelemetry/opentelemetry-javaagent.jar -Dotel.javaagent.configuration-file=orders-service/opentelemetry/otel-agent.properties
   ```

### Advantages
- No source code modification
- Easy to enable/disable

### Drawbacks
- Less control over instrumentation points
- Requires deployment with the agent

## 2. delivery-service: Dependency Instrumentation

Instrumentation is done by adding the OpenTelemetry dependency in the `pom.xml` and configuring observability in the code and Spring Boot configuration files.

### Main Steps:
1. Add OpenTelemetry dependencies in `pom.xml`.
2. Configure trace export in `application.yaml`.
3. (Optional) Add code to trace custom operations.

### Advantages
- Fine-grained control over instrumentation (add custom spans)
- Native integration with Spring Boot

### Drawbacks
- Requires source code changes
- More maintenance as code evolves

## Conclusion

- Use the **Java agent** for quick integration without code changes.
- Use **dependency instrumentation** for advanced control and custom observability.

Both approaches can coexist depending on your microservice needs.

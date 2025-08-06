FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/frequencyMonitoring-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "frequencyMonitoring-0.0.1-SNAPSHOT.jar"]

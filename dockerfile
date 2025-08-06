# Estágio de Build: Usa uma imagem Maven com JDK 21 para compilar o projeto
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Estágio de Execução: Usa uma imagem Java 21 JRE leve para rodar a aplicação
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/frequencyMonitoring-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "frequencyMonitoring-0.0.1-SNAPSHOT.jar"]
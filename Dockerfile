FROM eclipse-temurin:23-jdk-alpine
LABEL authors="Eduardo Maravilla"
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#docker build --platform=linux/amd64 -t eduardomaravill/sdd-catalog-sb:latest .
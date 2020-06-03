FROM maven:3.6.3-ibmjava-8-alpine AS mavenService
WORKDIR /usr/src/server
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=mavenService /usr/src/server/target/zuul-api-gateway.jar ./
EXPOSE 8079
ENTRYPOINT ["java", "-jar", "zuul-api-gateway.jar"]

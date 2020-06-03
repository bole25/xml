FROM maven:3.6.3-ibmjava-8-alpine AS mavenService
WORKDIR /usr/src/server
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=mavenService /usr/src/server/target/eureka-server.jar ./
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]

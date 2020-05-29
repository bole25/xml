FROM openjdk:8
ADD target/request-service.jar request-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "request-service.jar"]

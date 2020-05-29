FROM openjdk:8
ADD target/messaging-service.jar messaging-service.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "messaging-service.jar"]

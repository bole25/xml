FROM openjdk:8
ADD target/zuul-api-gateway.jar zuul-api-gateway.jar
EXPOSE 8079
ENTRYPOINT ["java", "-jar", "zuul-api-gateway.jar"]

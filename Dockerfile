FROM openjdk:8
ADD target/authentication-authorization-service.jar authentication-authorization-service.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","authentication-authorization-service.jar"]
FROM openjdk:10
ADD target/blockchain-1.0-spring-boot.jar /blockchain-1.0-spring-boot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blockchain-1.0-spring-boot.jar"]
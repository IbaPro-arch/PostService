FROM openjdk:11

ADD target/docker-spring.jar docker-spring.jar

EXPOSE 9095

ENTRYPOINT ["java", "-jar", "docker-spring.jar"]
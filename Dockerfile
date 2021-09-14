FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar search.jar
ENTRYPOINT ["java","-jar","/search.jar"]
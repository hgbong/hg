FROM java:8
EXPOSE 8080
ARG JAR_FILE=target/hg-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} hg.jar
ENTRYPOINT ["java","-jar", "/hg.jar"]
FROM openjdk:8
ARG JAR_FILE=target/demodocker1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} docker2.jar
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-jar","/docker2.jar"]
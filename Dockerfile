FROM openjdk:17
ADD target/MServiceDemo-0.0.1-SNAPSHOT.jar MServiceDemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "MServiceDemo-0.0.1-SNAPSHOT.jar"]

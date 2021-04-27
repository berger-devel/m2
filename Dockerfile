FROM openjdk:8
COPY ./target/m2-0.0.1-SNAPSHOT.jar m2-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","m2-0.0.1-SNAPSHOT.jar"]

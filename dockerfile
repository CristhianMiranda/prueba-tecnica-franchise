FROM openjdk:17
COPY franchise-core-app/build/libs/franchise-core-app-0.0.1-SNAPSHOT.jar franchise.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "franchise.jar"]

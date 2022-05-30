FROM openjdk:18
COPY ./build/libs/produto-app-0.0.1-SNAPSHOT.jar produto-app-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "alunos-api-0.0.1-SNAPSHOT.jar"]
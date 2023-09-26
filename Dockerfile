FROM openjdk:11
LABEL authors="sonam.shrestha"
COPY ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

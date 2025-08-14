FROM eclipse-temurin:latest
WORKDIR /app
COPY . /app
EXPOSE 8080
RUN ./gradlew build
CMD ["java", "-jar", "app/build/libs/app-all.jar"]
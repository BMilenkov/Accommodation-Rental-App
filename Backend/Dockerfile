FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests \
 && mv target/*.jar app.jar
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]

# Use official OpenJDK 17 image
FROM maven:3.8.6-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy pom.xml
COPY pom.xml .

# Copy source code
COPY src src

# Build the application
RUN mvn clean package

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/SimpleBank-1.0-SNAPSHOT.jar"]
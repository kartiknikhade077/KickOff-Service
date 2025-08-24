# Step 1: Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy built jar into container
COPY target/*.jar app.jar

# Step 4: Expose application port
EXPOSE 8088

# Step 5: Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]

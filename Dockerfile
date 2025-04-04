# Step 1: Use Maven to build the application
FROM maven:3.8.6-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file (pom.xml)
COPY pom.xml .

# Download the project dependencies (this is to avoid re-downloading dependencies if they are cached)
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src /app/src

# Build the application (this will create the WAR file)
RUN mvn clean install -DskipTests

# Use OpenJDK 11 as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY --from=build /app/target/coveragex-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

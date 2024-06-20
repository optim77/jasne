# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file to the container
COPY dstay-0.0.1-SNAPSHOT.jar /app/backend-app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "backend-app.jar"]
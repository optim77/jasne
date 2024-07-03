# Use an official OpenJDK runtime as a parent image
FROM openjdk:18-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file to the container
COPY ./backend/dstay-0.0.7-SNAPSHOT.jar /app/backend-app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
#ENTRYPOINT ["java", "-jar", "backend-app.jar"]
ENTRYPOINT ["java", "-jar", "backend-app.jar", "--spring.datasource.url=jdbc:postgresql://db:5432/jasne", "--spring.datasource.username=postgres", "--spring.datasource.password=root", "--spring.jpa.hibernate.ddl-auto=create", "--spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect", "--spring.sql.init.mode=always"]

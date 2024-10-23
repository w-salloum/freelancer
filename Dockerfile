# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/freelancer-0.0.1-SNAPSHOT.jar /app/freelancer.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/freelancer.jar"]

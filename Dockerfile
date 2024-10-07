# Use an official JDK as a parent image
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code into the container
COPY src ./src

# Install wget and curl
RUN apt-get update && \
    apt-get install -y wget curl unzip && \
    rm -rf /var/lib/apt/lists/*

# Download Allure CLI for version 2.30.0
RUN echo "Downloading Allure CLI..." && \
    curl -L -o allure-2.30.0.zip https://github.com/allure-framework/allure2/releases/download/2.30.0/allure-2.30.0.zip || { echo "Failed to download Allure CLI"; exit 1; }

# Unzip Allure CLI
RUN echo "Unzipping Allure CLI..." && \
    unzip allure-2.30.0.zip -d /opt || { echo "Failed to unzip Allure CLI"; exit 1; }

# Create symlink for Allure CLI
RUN echo "Creating symlink for Allure CLI..." && \
    ln -s /opt/allure-2.30.0/bin/allure /usr/bin/allure || { echo "Failed to create symlink for Allure CLI"; exit 1; }

# Remove the zip file
RUN echo "Cleaning up Allure zip file..." && \
    rm allure-2.30.0.zip

# Package the application (this will create a jar file)
RUN mvn clean package

# Use a smaller image to run the application
FROM openjdk:11-jre-slim

# Set the working directory for the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Define the entry point
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

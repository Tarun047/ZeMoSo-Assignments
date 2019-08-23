FROM openjdk:11-jre-slim

# Add Maintainer Info
LABEL maintainer="gtarun047@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=App.jar

# Add the application's jar to the container
ADD ${JAR_FILE} App.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/App.jar"]

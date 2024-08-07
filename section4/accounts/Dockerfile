# Start with base image containing Java Runtime
FROM openjdk:17-jdk-slim

# Infor about who maintains this image
MAINTAINER jayoswal.com

# Copy application's jar to image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar


# Execute the jar using the java runtime
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
# Stage 1: Build with Gradle on a Debian-based image
FROM gradle:7.6-jdk21-slim AS builder

# Set the working directory
WORKDIR /home/gradle/project

# Copy Gradle wrapper and build scripts first (for better layer caching)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Download dependencies (caches this layer unless build files change)
RUN ./gradlew --no-daemon dependencies

# Copy the rest of the source and build the fat JAR
COPY src src
RUN ./gradlew --no-daemon bootJar -x test

# Stage 2: Runtime image using a slim Alpine-based JRE
FROM eclipse-temurin:21-jre-alpine

# Create a non-root user for safety
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Create app directory
WORKDIR /app

# Copy the fat JAR from the builder stage
COPY --from=builder /home/gradle/project/build/libs/visitor-insights-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Set a sensible default for JVM memory (optional)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Run the JAR
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
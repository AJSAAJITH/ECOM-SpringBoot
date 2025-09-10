# --- Build stage ---
 # Use Java 17 JDK base image for building (Maven needs JDK to compile)
FROM eclipse-temurin:17-jdk AS builder
# Set working directory inside container to /build
WORKDIR /build
# Copy everything from your project folder (host) to /build (container)
COPY . .
# Run Maven wrapper to build the jar file (skip tests to save time)
RUN ./mvnw clean package -DskipTests

# --- Runtime stage ---
# Use smaller Java 17 JRE base image for running (lighter than JDK)
FROM eclipse-temurin:17-jre
# Set working directory inside container to /app
WORKDIR /app

# Copy the built jar from the builder stage into runtime container
COPY --from=builder /build/target/Ecom-0.0.1-SNAPSHOT.jar app.jar

# Copy production properties file into the runtime container
COPY ./src/main/resources/application-prod.properties /app/config/application-prod.properties

# Copy environment file (optional, so Spring can read env variables if needed)
COPY ./.env /app

# Define environment variables for Spring Boot
  # Tell Spring where to load config file from
ENV SPRING_CONFIG_LOCATION=/app/config/application-prod.properties
  # Run Spring Boot in 'prod' profile
ENV SPRING_PROFILES_ACTIVE=prod

# Define container entrypoint (how the app runs when container starts)
ENTRYPOINT ["java", "-jar", "app.jar"]

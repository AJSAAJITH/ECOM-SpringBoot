# --- Build stage ---
FROM eclipse-temurin:17-jdk AS builder   # Use Java 17 JDK base image for building (Maven needs JDK to compile)
WORKDIR /build                           # Set working directory inside container to /build
COPY . .                                 # Copy everything from your project folder (host) to /build (container)
RUN ./mvnw clean package -DskipTests     # Run Maven wrapper to build the jar file (skip tests to save time)

# --- Runtime stage ---
FROM eclipse-temurin:17-jre              # Use smaller Java 17 JRE base image for running (lighter than JDK)
WORKDIR /app                             # Set working directory inside container to /app

# Copy the built jar from the builder stage into runtime container
COPY --from=builder /build/target/Ecom-0.0.1-SNAPSHOT.jar app.jar

# Copy production properties file into the runtime container
COPY ./src/main/resources/application-prod.properties /app/config/application-prod.properties

# Copy environment file (optional, so Spring can read env variables if needed)
COPY ./.env /app

# Define environment variables for Spring Boot
ENV SPRING_CONFIG_LOCATION=/app/config/application-prod.properties  # Tell Spring where to load config file from
ENV SPRING_PROFILES_ACTIVE=prod                                    # Run Spring Boot in 'prod' profile

# Define container entrypoint (how the app runs when container starts)
ENTRYPOINT ["java", "-jar", "app.jar"]

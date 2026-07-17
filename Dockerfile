# Use a small, supported JRE image
FROM amazoncorretto:17

# Metadata
LABEL maintainer="satishyadav"
WORKDIR /app

# Copy the application jar (match developer-app-0.0.1*.jar)
# The build context must include the 'target' directory (run docker build from repo root)


COPY target/developer-app-0.0.1-SNAPSHOT.jar.original app.jar
COPY src/main/resources/* /app/config/
COPY target/lib /app/lib

# Expose Spring Boot default port (change if configured differently)
EXPOSE 8080

# Optional: allow passing additional JVM options via env var
ENV JAVA_OPTS=""

# Healthcheck (optional, adjust path /actuator/health if using actuator)
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s \
  CMD wget -q -O- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java","-jar","app.jar"]
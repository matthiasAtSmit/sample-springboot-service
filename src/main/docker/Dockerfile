FROM openjdk:8u181-jre-alpine3.8

# No entrypoint set
ENTRYPOINT []

# Exposed default port
EXPOSE 8080

# Copy local folders to docker container
COPY . /

# Fix permissions on run-java.sh
RUN chmod 0755 /opt/app/run-java.sh
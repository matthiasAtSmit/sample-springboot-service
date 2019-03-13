FROM openjdk:8-jre
EXPOSE 8080 5005
COPY ./target/sample-service-0.0.1-SNAPSHOT.jar /opt/target
WORKDIR /opt/target
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

CMD ["java", "-jar", "sample-service-0.0.1-SNAPSHOT.jar"]
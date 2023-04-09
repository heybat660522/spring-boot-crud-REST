FROM adoptopenjdk/openjdk8

VOLUME /tmp

COPY target/SpringBootDemo-0.0.1-SNAPSHOT.jar /app.jar
RUN sh -c 'touch /app.jar'

EXPOSE 8080

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]


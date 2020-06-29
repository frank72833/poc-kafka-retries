FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8080

COPY target/*.jar /opt/
CMD $JAVA_HOME/bin/java $JAVA_OPTS -jar /opt/*.jar
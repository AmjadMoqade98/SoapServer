FROM openjdk:8
ADD build/libs/soapServer-0.0.1-SNAPSHOT.jar soapServer-0.0.1-SNAPSHOT.jar
EXPOSE 8099
ENTRYPOINT ["java" , "-jar" , "soapServer-0.0.1-SNAPSHOT.jar"]
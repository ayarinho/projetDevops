FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-5.0.jar timesheet-5.0.jar
ENTRYPOINT ["java","-jar","/timesheet-5.0.jar"]
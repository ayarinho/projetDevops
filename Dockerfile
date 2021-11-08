FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-2.0.war timesheet-2.0.war
ENTRYPOINT ["java","-jar","/timesheet-2.0.war"]
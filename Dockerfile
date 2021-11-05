FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-1.0.6.war timesheet-1.0.6.war
ENTRYPOINT ["java","-jar","/timesheet-1.0.6.war"]
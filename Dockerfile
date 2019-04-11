FROM openjdk:11-jre-slim
MAINTAINER Esa Rijal <esarijal_mustaqbal@mitrais.com>

ARG JAR_FILE
COPY $JAR_FILE /app.jar
ENV UPLOAD_URL=http://172.30.129.33:8000/upload
CMD ["java","-jar","/app.jar"]
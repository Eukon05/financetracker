FROM openjdk:17-jdk-alpine

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait

COPY target/financetracker.jar app.jar

CMD /wait && java -jar /app.jar
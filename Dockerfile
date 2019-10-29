FROM openjdk:8-jdk-alpine
MAINTAINER mateusstp@gmail.com

RUN mkdir -p /opt/starwars

ADD env.conf /opt/starwars/env.conf
ADD src/main/resources/config/application.yml /opt/starwars/application.yml
ADD start-app.sh /opt/starwars/start-app.sh
ADD target/starwars-0.0.1.jar /opt/starwars/starwars.jar

RUN chmod +x /opt/starwars/env.conf
RUN chmod +x /opt/starwars/start-app.sh
RUN chmod +x /opt/starwars/starwars.jar
WORKDIR /opt/starwars

EXPOSE 8080

ENTRYPOINT ["/opt/starwars/start-app.sh"]

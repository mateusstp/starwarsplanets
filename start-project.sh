#!/bin/ash
mvn clean install -Pprod
docker build -t startwars .
docker-compose -f compose.yml  down
docker-compose -f compose.yml  up -d

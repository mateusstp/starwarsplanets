#!/bin/ash

source env.conf

java ${JAVA_OPTS} -jar ${APP_HOME}/${APP_NAME} --server.port=${HTTP_PORT}
FROM maven:latest
MAINTAINER Bruno Romao Antunes
COPY . /transation-statistics-challenge
EXPOSE 8080
WORKDIR /transation-statistics-challenge
ENTRYPOINT mvn spring-boot:run
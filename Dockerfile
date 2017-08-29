FROM openjdk:jre-alpine

RUN apk update && apk add vim procps

COPY build/libs/WeatherService-1.0-SNAPSHOT.jar /irdeto-oss/WeatherService.jar

WORKDIR irdeto-oss

ENTRYPOINT ["java"]
CMD ["-server", "-Xmx128M", "-jar", "WeatherService.jar"]

FROM gradle:7.5.1-jdk11-alpine as build

RUN mkdir /home/gradle/src

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR  /home/gradle/src

RUN gradle clean build -x test --no-daemon

FROM  amazoncorretto:11-alpine3.13

RUN mkdir /home/app

WORKDIR /home/app

COPY --from=build /home/gradle/src/yh-core-basic/build/libs/yh-core-basic.jar /app/spring-boot-application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar",  "/app/spring-boot-application.jar"]




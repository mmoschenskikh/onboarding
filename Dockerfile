FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle fatJar

FROM openjdk:11
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/rosmolodez-onboarding.jar

ARG bot_token
ENV TELEGRAM_BOT_TOKEN=$bot_token
ARG bot_name
ENV TELEGRAM_BOT_USERNAME=$bot_name

ENTRYPOINT ["java","-jar","/app/rosmolodez-onboarding.jar"]

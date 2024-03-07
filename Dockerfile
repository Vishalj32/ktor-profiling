FROM openjdk:17.0.2-jdk AS build

RUN mkdir /tmpBuild
COPY . /tmpBuild

WORKDIR /tmpBuild

RUN ./gradlew clean build

FROM openjdk:17.0.2-jdk

COPY --from=build /tmpBuild/build/libs/ktor-profiling*all.jar /app/ktor-profiling.jar
WORKDIR /app

CMD ["sh", "-c","java -jar ktor-profiling.jar"]
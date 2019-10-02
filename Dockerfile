FROM openjdk:11-jdk
VOLUME /tmp
WORKDIR /app
COPY . /app
CMD ["./gradlew", "build", "docker"]
ENTRYPOINT ["java","-jar","/app/build/libs/gs-spring-boot-0.1.0.jar"]
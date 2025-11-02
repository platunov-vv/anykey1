FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /anykey1
COPY . /anykey1/.
RUN pwd
RUN mvn -f /anykey1/pom.xml clean package -Dmaven.test.skip=true && ls -l

FROM eclipse-temurin:17-jre-alpine
WORKDIR /anykey1
COPY --from=builder /anykey1/target/*.jar /anykey1/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/anykey1/app.jar"]
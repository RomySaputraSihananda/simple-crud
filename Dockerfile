FROM maven:3.8.3-openjdk-17

WORKDIR /app

COPY . /app

RUN mvn clean install package

EXPOSE 4444

CMD ["java", "-jar", "target/simple-crud.jar"]

FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /backend

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean compile
RUN mvn dependency:copy-dependencies -DoutputDirectory=target/dependency

FROM eclipse-temurin:21-jre

WORKDIR /backend

COPY --from=build /backend/target/classes ./classes
COPY --from=build /backend/target/dependency ./dependency

CMD ["java", "-cp", "classes:dependency/*", "com.alanpmz.pharmacy_CRUD_spring.PharmacyCrudSpringApplication"]
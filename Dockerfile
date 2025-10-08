FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /backend

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean compile
RUN mvn dependency:copy-dependencies -DoutputDirectory=target/dependency

FROM eclipse-temurin:21-jre

WORKDIR /backend

USER root
RUN apt-get update && \
    apt-get install -y openssl sed && \
    rm -rf /var/lib/apt/lists/*

COPY --from=build /backend/target/classes ./classes
COPY --from=build /backend/target/dependency ./dependency

COPY generate_keys.sh .

RUN sed -i 's/\r$//' generate_keys.sh && chmod +x generate_keys.sh


ENTRYPOINT ["/backend/generate_keys.sh"]
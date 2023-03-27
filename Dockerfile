FROM openjdk

WORKDIR /app

COPY target/order-api.jar /app/order-api.jar

ENTRYPOINT ["java", "-jar", "order-api.jar"]
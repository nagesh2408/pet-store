FROM openjdk:8
ADD target/pet-store-0.0.1-SNAPSHOT.war pet-store.war
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "pet-store.war"]
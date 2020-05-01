FROM maven:3.3-jdk-8
COPY . /
RUN mvn clean install
CMD java -jar /target/game.jar
FROM --platform=$BUILDPLATFORM maven:3.8.6-eclipse-temurin-17-alpine as build

WORKDIR /home/backend
COPY pom.xml /home/backend/pom.xml
RUN mvn dependency:go-offline

COPY src /home/backend/src
RUN --mount=type=cache,target=/root/.m2 mvn clean install -DskipTests
RUN mkdir -p target/dependency
WORKDIR /home/backend/target/dependency
RUN jar -xf ../*.jar

##############################################################################

FROM eclipse-temurin:17-jdk-alpine as setup

# RUN apk --no-cache add zsh bash git
# RUN cd ~ && wget https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh && sh install.sh
# RUN /bin/zsh ~/.zshrc

VOLUME /tmp
ARG DEPENDENCY=/home/backend/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.moneyapi.MoneyApiApplication"]
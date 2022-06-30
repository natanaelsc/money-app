FROM --platform=$BUILDPLATFORM maven:3.8.6-eclipse-temurin-8-alpine as build

RUN apk add --no-cache bash

WORKDIR /home/backend
COPY pom.xml /home/backend/pom.xml
RUN mvn dependency:go-offline

COPY src /home/backend/src
RUN --mount=type=cache,target=/root/.m2 mvn clean install -DskipTests
# RUN mvn install
RUN mkdir -p target/dependency
WORKDIR /home/backend/target/dependency
RUN jar -xf ../*.jar

# ##############################################################################

FROM eclipse-temurin:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=/home/backend/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.moneyapi.MoneyApiApplication"]

# FROM openjdk:8-jdk-alpine as build

# RUN apk add --no-cache bash

# WORKDIR /home/backend

# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./

# RUN ./mvnw dependency:go-offline

# COPY src ./src

# RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# # ##############################################################################

# FROM openjdk:8-jdk-alpine
# EXPOSE 8080
# VOLUME /tmp
# ARG DEPENDENCY=/home/backend/target/dependency
# COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.moneyapi.MoneyApiApplication"]

# ENTRYPOINT ["java","-cp","app:app/lib/*","org.springframework.boot.devtools.RemoteSpringApplication"]

# ##############################################################################

# WORKDIR /home/backend
 
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./

# RUN ./mvnw dependency:go-offline

# COPY src ./src

# CMD ["./mvnw", "spring-boot:run"]

# ##############################################################################

# RUN chmod +x .docker/entrypoint.sh

# ##############################################################################

# RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# ARG DEPENDENCY=/home/app/target/dependency
# COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.moneyapi.MoneyApiApplication"]

# ##############################################################################
FROM eclipse-temurin:17-jdk-alpine as setup

RUN apk add --no-cache zsh git maven
RUN cd ~ && wget https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh && sh install.sh
RUN /bin/zsh ~/.zshrc
FROM alpine:edge
MAINTAINER coarsehorse
COPY . /arcnotes
RUN apk add --no-cache \
    openjdk11 \
    maven
WORKDIR /arcnotes
RUN mvn clean install
WORKDIR /arcnotes/target
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "arcnotes-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

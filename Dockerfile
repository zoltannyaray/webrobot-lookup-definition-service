FROM frolvlad/alpine-oraclejdk8:slim
WORKDIR /usr/share/webrobot-lookup-definition-service
ADD target/webrobot-lookup-definition-service.jar webrobot-lookup-definition-service.jar
RUN touch webrobot-lookup-definition-service.jar
EXPOSE 4002
ENTRYPOINT ["/usr/bin/java", "-jar", "webrobot-lookup-definition-service.jar"]

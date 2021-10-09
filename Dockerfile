FROM alpine
WORKDIR /root/edc
COPY . /root/edc

RUN apk add openjdk8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin

RUN javac Crawler.java

ENTRYPOINT java Crawler



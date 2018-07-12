FROM ubuntu:16.04

# Install Basic.
RUN \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update -y && \
  apt-get upgrade -y && \
  apt-get install -y build-essential && \
  apt-get install -y software-properties-common && \
  apt-get install -y git

# Install Java.
RUN \
  apt-get install -y default-jdk

# Install Maven
RUN \
  apt-get install -y maven && \
  rm -rf /var/lib/apt/lists/*

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# Set environment variables.
ENV HOME /root

# Define working directory.
WORKDIR /root

# Cloning and install dependencies
RUN \
  git clone https://github.com/GustavoDinizMonteiro/QMA-API.git && \
  (cd QMA-API && mvn install)

# Define working directory.
WORKDIR /root/QMA-API
  
CMD ./mvnw spring-boot:run
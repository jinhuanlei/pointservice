# Getting Started

## Environment Setup

We can run the program by either Java Virtual Machine or Docker. Docker solution should be cleanest
and easiest way for running this program.

### Run the service by JVM

1. install java 17

Option 1: install java by homebrew

```
brew tap bell-sw/liberica
brew install --cask liberica-jdk17
```

Option 2: install official java package
[link](https://bell-sw.com/pages/downloads/#/java-17-lts)

2. Verify Java Environment

```text
❯ java -version
openjdk version "17.0.3.1" 2022-04-22 LTS
OpenJDK Runtime Environment (build 17.0.3.1+2-LTS)
OpenJDK 64-Bit Server VM (build 17.0.3.1+2-LTS, mixed mode, sharing)
```

3. run the jar file
```clone the project and go to the project folder
java -jar pointservice-0.0.1-SNAPSHOT.jar
```

### Option 2: Run the service by Docker

### Trouble Shooting

For further reference, please consider the following sections:

* Have issue to switch different Java version, please
  check [this](https://stackoverflow.com/questions/26252591/mac-os-x-and-multiple-java-versions)

## Assumptions

1. Payer cannot go lower than negative points, so first transaction(with the earliest timestamp)
   will be rejected by server.
2. Cannot send transaction with 0 point.

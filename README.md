# digitalpolice-api
[![Build Status](http://162.211.230.155:8181/job/digitalpolice-java/badge/icon)](http://162.211.230.155:8181/job/digitalpolice-java/)

Digital Police API provides REST services that expose crimes/incidents in a format suitable for rendering on maps.

### Getting Started

#### Prerequisities

Java 1.7 or higher
Maven

#### Before building testing and running

Make sure you are in the project folder 'digitalpolice-api'

#### How to build

```
mvn clean install -DskipTests
```

#### How to test

```
mvn test
```

#### How to run locally

```
java -jar target/citypolice-0.0.1-SNAPSHOT.jar
```

### Documentation

Latest REST documentation is available by the [link](http://162.211.230.155:8080/docs/index.html).

### UAT environment

Latest stable version of the API is available [here](http://162.211.230.155:8080/).

### Continuous Integration

We are using Jenkins CI to build and deploy latest stable versions. Jenkins UI is available [here](http://162.211.230.155:8181/job/digitalpolice-java).

### Issues

Feel free to submit issues and enhancement requests in case they are not duplicating existing issues.

### How to contribute

Please read [CONTRIBUTING.md](https://github.com/digiU-DigitalPolice/digitalpolice-api/blob/master/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Project introduction

Sample web service project using spring boot

This project is for authorization management when a user calls specific APIs

It provides authority check function using ABAC strategy (benchmarked from AWS IAM)

When each user performs a specific action(API call), permission is determined from policies linked from himself or
groups to which user belongs

<br>

#### completion

- entity service implement (user, group, policy, role) : 10%
- pdp implement (determining policy)
- set metadata
- test
- authentication

## Spec

- java 1.13
- spring boot 2.6.3
- mvn 3.6.3
- jpa (2.6.3 same with spring boot)
- postgres 42.3.1
- swagger 2.9.2

## How to run

first, install app from git repository

```
git clone https://github.com/hgbong/hg.git
```

### using mvn, mvnw (Windows)

JAVA_HOME environment path needed

#### mvnw plugin

 ```
$ mvnw spring-boot:run
$ mvnw spring-boot:run -DskipTests
 ```

#### run with java -jar

```
$ mvnw clean && mvnw package
$ java -jar ./target/hg-0.0.1-SNAPSHOT.jar
```

### Intellij

1. import project with maven
2. set HgApplication as main class and run (no other configurations needed yet)

## blogs

https://pnt5468.tistory.com/
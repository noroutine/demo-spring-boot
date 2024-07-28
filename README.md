# Getting Started

### How-To

Spring Boot Starter with Azul Zuul and Docker build

```
./gradlew -iiS bootBuildImage --imageName=demo-spring-boot
docker run -t -i -p 8080:8080 demo-spring-boot -v ./data:/workspace/data
```

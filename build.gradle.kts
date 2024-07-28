import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(18)
    }
}

kotlin {
    jvmToolchain(18)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
//    imageName = "${dockerUrl}/${project.name}:${version}"
//    publish = true
//    docker {
//        tlsVerify = false
//        publishRegistry  {
//            username = "${dockerUsername}"
//            password = "${dockerPassword}"
//            url = "${dockerUrl}"
//            email = "${dockerEmail}"
//        }
//    }
    environment = mapOf(
        "BPE_DELIM_JAVA_TOOL_OPTIONS" to " ",
        "BP_JVM_VERSION" to "21",
//        "BPE_APPEND_JAVA_TOOL_OPTIONS" to "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8787",
//        "BPE_APPEND_JAVA_TOOL_OPTIONS" to "-Duser.timezone=America/New_York"
    )

    buildpacks = listOf(
//        "gcr.io/paketo-buildpacks/adoptium",
        "gcr.io/paketo-buildpacks/azul-zulu",
        "urn:cnb:builder:paketo-buildpacks/java"
    )
}
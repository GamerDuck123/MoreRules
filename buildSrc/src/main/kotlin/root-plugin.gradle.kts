plugins {
    `java-library`
    `maven-publish`
}

repositories {
    maven("https://jitpack.io/")

    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("21"))
}

base {
    archivesName.set("${rootProject.name}-${name}")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }
}
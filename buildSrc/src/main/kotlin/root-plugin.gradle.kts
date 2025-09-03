plugins {
    `java-library`
    `maven-publish`

    id("com.gradleup.shadow")
    id("com.modrinth.minotaur")
}

repositories {
    maven("https://jitpack.io/")

    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("21"))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }
}
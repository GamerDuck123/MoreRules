rootProject.name = "More Rules"
include("common", "paper", "fabric", "neoforge")

pluginManagement {
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://repo.papermc.io/repository/maven-public/")

        maven("https://maven.fabricmc.net/")
        maven("https://repo.opencollab.dev/maven-snapshots/")
        maven("https://repo.opencollab.dev/maven-releases/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
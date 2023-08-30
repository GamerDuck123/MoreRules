rootProject.name = "MoreRules"
include("common", "paper", "fabric")

pluginManagement {
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

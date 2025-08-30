plugins {
    id("paper-plugin")
}

group = "${rootProject.group}.paper"
version = rootProject.version

base {
    archivesName.set("${rootProject.name}-${project.name}")
}

dependencies {
    compileOnly(project(":common"))
    compileOnly("com.mojang:brigadier:1.2.9")
}

tasks {
    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "description" to project.properties["description"],
            "apiVersion" to "1.21.8"
        )

        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}
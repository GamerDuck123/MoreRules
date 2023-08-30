plugins {
    id("root-plugin")
}

group = "${rootProject.group}.forge"
version = rootProject.version

base {
    archivesName.set("${rootProject.name}-${project.name}")
}

dependencies {
    compileOnly(project(":common"))
}

tasks {
    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "description" to project.properties["description"],
        )

        filesMatching("forge.yml") {
            expand(props)
        }
    }
}
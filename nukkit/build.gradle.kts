plugins {
    id("root-plugin")
}

group = "${rootProject.group}.nukkit"
version = rootProject.version

base {
    archivesName.set("${rootProject.name}-${project.name}")
}

dependencies {
    compileOnly(project(":common"))
    compileOnly("cn.nukkit", "nukkit", project.properties["nukkitVersion"] as String)
}

tasks {
    processResources {
        val props = mapOf(
                "name" to rootProject.name,
                "group" to project.group,
                "version" to project.version,
                "description" to project.properties["description"],
                "apiVersion" to "1.0.5"
        )

        filesMatching("nukkit.yml") {
            expand(props)
        }
    }
}
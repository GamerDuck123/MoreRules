plugins {
    id("paper-plugin")
}

dependencies {
    compileOnly(libs.brigadier)
}

tasks {
    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "description" to project.property("description") as String,
            "apiVersion" to libs.versions.minecraft.get()
        )

        from("src/main/templates") {
            listOf(
                "paper-plugin.yml",
            ).forEach {
                filesMatching(it) {
                    expand(props)
                }
            }
        }
        into(layout.buildDirectory.dir("src/main/resources"))
    }
}
dependencies {
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
    compileOnly(libs.brigadier)
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
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
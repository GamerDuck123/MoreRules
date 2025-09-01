dependencies {
    minecraft(libs.minecraft)

    mappings("net.fabricmc:yarn:${libs.versions.yarn.get()}:v2")

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
}

loom {
//    splitEnvironmentSourceSets()
    accessWidenerPath.set(file("src/main/resources/${project.property("modid")}.fabric.accesswidener"))

    mods {
        create(project.property("modid").toString()) {
            sourceSet(sourceSets.main.get())
        }
    }

}

tasks {
    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "modid" to rootProject.property("modid"),
            "description" to project.property("description"),
            "fabricApiVersion" to libs.versions.api.get(),
            "fabricLoaderVersion" to libs.versions.loader.get(),
            "minecraftVersion" to libs.versions.minecraft.get(),
            "author" to project.property("author"),
            "website" to project.property("website"),
            "sources" to project.property("sources"),
            "issues" to project.property("issues")
        )

        from("src/main/templates") {
            listOf(
                "fabric.mod.json",
            ).forEach {
                filesMatching(it) {
                    expand(props)
                }
            }
        }
        into(layout.buildDirectory.dir("src/main/resources"))
    }
}
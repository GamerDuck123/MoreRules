neoForge {
    version = libs.versions.neo.get()

    parchment {
        mappingsVersion = libs.versions.parchment.mappings.get()
        minecraftVersion = libs.versions.minecraft.get()
    }

    mods {
        create(project.property("modid").toString()) {
            sourceSet(sourceSets.main.get())
        }
    }
}

// Sets up a dependency configuration called 'localRuntime'.
val localRuntime: Configuration by configurations.creating
configurations {
    runtimeClasspath.get().extendsFrom(localRuntime)
}
sourceSets {
    main {
        java {
            srcDir("../mixins/src/main/java")
            srcDir("../common/src/main/java")
        }
        resources {
            srcDir("../mixins/src/main/resources")
            srcDir("../common/src/main/resources")
        }
    }
}
tasks {
    processResources {
        val props = mapOf(
            "minecraft_version" to libs.versions.minecraft.get(),
            "minecraftVersionRange" to "[${libs.versions.minecraft.get()}]",
            "neo_version" to libs.versions.neo.get(),
            "mod_id" to "morerules",
            "mod_name" to rootProject.property("name"),
            "mod_license" to project.property("license"),
            "mod_version" to project.property("version"),
            "mod_author" to project.property("author"),
            "mod_description" to project.property("description")
        )

        from("src/main/templates") {
            listOf(
                "META-INF/neoforge.mods.toml",
            ).forEach {
                filesMatching(it) {
                    expand(props)
                }
            }
        }
        into(layout.buildDirectory.dir("src/main/resources"))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8" // Use the UTF-8 charset for Java compilation
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
neoForge {
    // Specify the version of NeoForge to use.
    version = libs.versions.neo.get()

    parchment {
        mappingsVersion = libs.versions.parchment.mappings.get()
        minecraftVersion = libs.versions.minecraft.get()
    }

    runs {
        create("client") {
            client()

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("modid") as String)
        }

        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("modid") as String)
        }

        // This run config launches GameTestServer and runs all registered gametests, then exits.
        create("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("modid") as String)
        }

        create("data") {
            clientData()

            // example of overriding the workingDirectory set in configureEach above, uncomment if you want to use it
            // gameDirectory = project.file("run-data")

            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            programArguments.addAll(
                "--mod", project.property("modid") as String,
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            // Recommended logging data for a userdev environment
            systemProperty("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            logLevel = org.slf4j.event.Level.DEBUG
        }
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

tasks {
    processResources {
        val props = mapOf(
            "minecraft_version" to libs.versions.minecraft.get(),
            "minecraftVersionRange" to "[${libs.versions.minecraft.get()}]",
            "neo_version" to libs.versions.neo.get(),
            "mod_id" to project.property("modid"),
            "mod_name" to project.property("name"),
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

//// To avoid having to run "generateModMetadata" manually, make it run on every project reload
//neoForge.ideSyncTask(generateModMetadata)

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8" // Use the UTF-8 charset for Java compilation
}

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
import net.neoforged.nfrtgradle.CreateMinecraftArtifacts

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

modrinth {
    uploadFile.set(tasks.jar)
}

val localRuntime: Configuration by configurations.creating
configurations {
    runtimeClasspath.get().extendsFrom(localRuntime)
}


tasks.register<Copy>("copyCommonSources") {
    from("$rootDir/mixins/src/main/java") {
        into("mixins/java")
    }
    from("$rootDir/common/src/main/java") {
        into("common/java")
    }
    from("$rootDir/mixins/src/main/resources") {
        into("mixins/resources")
    }
    from("$rootDir/common/src/main/resources") {
        into("common/resources")
    }

    into("${layout.buildDirectory}/generated/sources")
}

sourceSets {
    main {
        java {
            srcDir("${layout.buildDirectory}/generated/sources/mixins/java")
            srcDir("${layout.buildDirectory}/generated/sources/common/java")
        }
        resources {
            srcDir("${layout.buildDirectory}/generated/sources/mixins/resources")
            srcDir("${layout.buildDirectory}/generated/sources/common/resources")
        }
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn("copyCommonSources")
}

tasks.named<ProcessResources>("processResources") {
    dependsOn("copyCommonSources")
}

tasks.named<CreateMinecraftArtifacts>("createMinecraftArtifacts") {
    dependsOn("copyCommonSources")
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
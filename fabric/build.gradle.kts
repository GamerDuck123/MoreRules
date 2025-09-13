dependencies {
    minecraft(libs.minecraft)

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21.8:2025.07.20@zip")
    })
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
}

modrinth {
    uploadFile.set(project.tasks.remapJar)
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


loom {
//    splitEnvironmentSourceSets()
    accessWidenerPath.set(file("../mixins/src/main/resources/${project.property("modid")}.accesswidener"))

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
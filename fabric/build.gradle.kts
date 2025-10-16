dependencies {
    minecraft(libs.minecraft)

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.minecraft.get()}:${libs.versions.parchment.mappings.get()}@zip")
    })
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
}

modrinth {
    uploadFile.set(project.tasks.remapJar)
}

tasks.register("publishCurseForge", net.darkhax.curseforgegradle.TaskPublishCurseForge::class) {
    apiToken = System.getenv("CURSEFORGE_TOKEN") as String?

    val projectId = findProperty("curseforgeID") as String?

    val mainFile = upload(projectId, project.tasks.remapJar)
    mainFile.addModLoader("Fabric")
    mainFile.addGameVersion(rootProject.property("minecraftVersion") as String)
    mainFile.releaseType = "release"
    mainFile.displayName = "${project.version as String}-${project.name}"
    mainFile.changelog = rootProject.file("CHANGELOG.md").readText()
}

tasks.register<Copy>("copyCommonSources") {
    from("$rootDir/common/src/main/java") {
        into("common/java")
    }


    from("$rootDir/common/src/main/resources") {
        exclude("META-INF/**")
        exclude("templates/**")
        into("common/resources")
    }

    from("$rootDir/common/src/main/resources/templates") {
        include("morerules.properties")
        into("common/resources")

        filesMatching("**/morerules.properties") {
            expand(mapOf(
                "default_path" to "mods/MoreRules/storage",
            ))
        }
    }

    into("${layout.buildDirectory}/generated/sources")
}

sourceSets {
    main {
        java {
            srcDir("${layout.buildDirectory}/generated/sources/common/java")
        }
        resources {
            srcDir("${layout.buildDirectory}/generated/sources/common/resources")
        }
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn("copyCommonSources")
}

loom {
//    splitEnvironmentSourceSets()
    accessWidenerPath.set(file("../common/src/main/resources/${project.property("modid")}.accesswidener"))

    mods {
        create(project.property("modid").toString()) {
            sourceSet(sourceSets.main.get())
        }
    }

}

tasks {
    processResources {
        dependsOn("copyCommonSources")
        val props = mapOf(
            "name" to rootProject.property("modid"),
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
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

tasks.register("publishCurseForge", net.darkhax.curseforgegradle.TaskPublishCurseForge::class) {
    apiToken = System.getenv("CURSEFORGE_TOKEN") as String?

    val projectId = findProperty("curseforgeID") as String?

    val mainFile = upload(projectId, tasks.jar)
    mainFile.addModLoader("NeoForge")
    mainFile.addGameVersion(rootProject.property("minecraftVersion") as String)
    mainFile.releaseType = "release"
    mainFile.displayName = "${project.version as String}-${project.name}"
    mainFile.changelog = rootProject.file("CHANGELOG.md").readText()
}

val localRuntime: Configuration by configurations.creating
configurations {
    runtimeClasspath.get().extendsFrom(localRuntime)
}


tasks.register<Copy>("copyCommonSources") {
    from("$rootDir/common/src/main/java") {
        exclude("me/gamerduck/rules/mixin/mixins/WitherBossMixin.java")
        into("common/java")
    }
    from("$rootDir/common/src/main/resources") {
        exclude("morerules.accesswidener")
        exclude("templates/**")
        into("common/resources")
    }

    from("$rootDir/common/src/main/resources/templates") {
        include("morerules.properties")
        into("common/resources")
        includeEmptyDirs = false

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

tasks.named<CreateMinecraftArtifacts>("createMinecraftArtifacts") {
    dependsOn("copyCommonSources")
}


tasks {
    processResources {
        dependsOn("copyCommonSources")
        val props = mapOf(
            "minecraft_version" to libs.versions.minecraft.get(),
            "minecraftVersionRange" to "[${libs.versions.minecraft.get()}]",
            "neo_version" to libs.versions.neo.get(),
            "mod_id" to rootProject.property("modid"),
            "mod_name" to rootProject.property("name"),
            "mod_license" to project.property("license"),
            "mod_version" to project.property("version").toString().replace("v", ""),
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
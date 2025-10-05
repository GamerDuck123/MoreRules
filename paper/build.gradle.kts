import io.papermc.hangarpublishplugin.model.Platforms

dependencies {
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
    compileOnly(libs.brigadier)
}

modrinth {
    uploadFile.set(tasks.jar)
}


hangarPublish {
    publications.register("plugin") {
        version.set(project.version as String)
        channel.set("Release")
        id.set("MoreRules")
        apiKey.set(System.getenv("HANGAR_API_TOKEN"))
        changelog.set(rootProject.file("CHANGELOG.md").readText())
        platforms {
            register(Platforms.PAPER) {
                jar.set(tasks.jar.flatMap { it.archiveFile })

                val versions: List<String> = (property("minecraftVersion") as String)
                    .split(",")
                    .map { it.trim() }
                platformVersions.set(versions)
            }
        }
    }
}

tasks.register<Copy>("copyCommonSources") {
    from("$rootDir/common/src/main/java") {
        exclude("me/gamerduck/rules/mixin/**")
        into("common/java")
    }
    from("$rootDir/common/src/main/resources") {
        exclude("META-INF/**")
        exclude("templates/**")
        exclude("morerules.accesswidener")
        exclude("morerules.mixins.json")
        into("common/resources")
    }

    from("$rootDir/common/src/main/resources/templates") {
        include("morerules.properties")      // only copy that file
        into("common/resources")      // place it directly into common/resources
        includeEmptyDirs = false

        filesMatching("**/morerules.properties") {
            expand(mapOf(
                "default_path" to "plugins/MoreRules/storage",
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

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    processResources {
        dependsOn("copyCommonSources")
        val props = mapOf(
            "name" to rootProject.property("modid"),
            "group" to project.group,
            "version" to project.version,
            "description" to rootProject.property("description") as String,
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
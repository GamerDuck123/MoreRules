dependencies {
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
    compileOnly(libs.brigadier)
}

modrinth {
    uploadFile.set(tasks.jar)
}


tasks.register<Copy>("copyCommonSources") {
    from("$rootDir/common/src/main/java") {
        into("common/java")
    }
    from("$rootDir/common/src/main/resources") {
        into("common/resources")
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

tasks.named<ProcessResources>("processResources") {
    dependsOn("copyCommonSources")
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
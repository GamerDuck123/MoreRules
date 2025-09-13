plugins {
    id("root-plugin")
}

defaultTasks("build")

rootProject.group = project.property("group") as String
rootProject.version = project.property("version") as String
rootProject.description = project.property("description") as String

allprojects {
    if (this.name != rootProject.name) {
        project.version = rootProject.version
        project.group = "${rootProject.group}.${this.name}"

        if (this.name == "paper") {
            apply(plugin = "paper-plugin")
        }

        if (this.name == "fabric") {
            apply(plugin = "fabric-plugin")
        }

        if (this.name == "neoforge") {
            apply(plugin = "neoforge-plugin")
        }

        if (this.name == "common") {
            apply(plugin = "root-plugin")
        }


        if (this.name == "mixins") {
            apply(plugin = "mixins-plugin")
        }

        base {
            archivesName.set("${rootProject.name}-${name}")
        }

        if (this.name != "common") {
            if (this.name != "mixins") {
                modrinth {
                    versionNumber.set("${version as String}-${name}")
                    loaders.addAll(
                        when (name) {
                            "fabric" -> listOf("fabric")
                            "neoforge" -> listOf("neoforge")
                            "paper" -> listOf("paper", "spigot", "bukkit", "purpur")
                            else -> throw IllegalStateException("Unknown loader $name")
                        }
                    )
                    token.set(System.getenv("MODRINTH_TOKEN"))
                    projectId.set("DaNtdRka")
                    versionType.set("release")
                    gameVersions.addAll("1.21.8")
                    syncBodyFrom.set(rootProject.file("README.md").readText())
                    changelog.set(rootProject.file("CHANGELOG.md").readText())
                }
            }
        }
    }

}


tasks {
    publish {
        dependsOn(subprojects.filter { it.name !in listOf("common", "mixins") }.map { it.tasks.named("modrinth") })
    }

    assemble {
        dependsOn(subprojects.filter { it.name !in listOf("common", "mixins") }.map {
            it.tasks.named("clean")
            it.tasks.named("copyCommonSources")
            it.tasks.named("processResources")
            it.tasks.named("build")
        })
    }
    register<Copy>("copyCommonSources") {
        dependsOn(subprojects.filter { it.name !in listOf("common", "mixins") }.map {
            it.tasks.named("copyCommonSources")
        })
    }
}
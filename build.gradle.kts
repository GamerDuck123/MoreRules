plugins {
    id("root-plugin")
}

defaultTasks("build")

rootProject.group = project.property("group") as String
rootProject.version = project.property("version") as String
rootProject.description = project.property("description") as String

//val combine = tasks.register<Jar>("combine") {
//    mustRunAfter("build")
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//    val jarFiles = subprojects.flatMap { subproject ->
//        files(subproject.layout.buildDirectory.file("libs/${rootProject.name}-${subproject.name}-${subproject.version}.jar").get())
//    }.filter { it.name != "MANIFEST.MF" }.map { file ->
//        if (file.isDirectory) file else zipTree(file)
//    }
//
//    from(jarFiles)
//}

//tasks.named("modrinth") {
//    dependsOn(tasks.named("combine"))
//}


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

        if (this.name == "neoforged") {
            apply(plugin = "neo-forge")
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
            dependencies {
                implementation(project(":common"))
            }
            if (this.name != "mixins") {
                modrinth {
                    versionNumber.set("${version as String}-${name}")
                    loaders.addAll(
                        when (name) {
                            "fabric" -> listOf("fabric")
                            "neoforged" -> listOf("neoforge")
                            "paper" -> listOf("paper", "spigot", "bukkit", "purpur")
                            else -> throw IllegalStateException("Unknown loader $name")
                        }
                    )
                    uploadFile.set(tasks.jar)
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
        dependsOn(subprojects.filter { it.name in listOf("fabric", "neoforged", "paper") }.map { it.tasks.named("modrinth") })
    }

    assemble {
        subprojects.forEach { project ->
            if (project.name != "mixins") {
                dependsOn(":${project.name}:clean")
                dependsOn(":${project.name}:processResources")
                dependsOn(":${project.name}:build")
            }
        }

//        finalizedBy(combine)
    }
}
plugins {
    id("root-plugin")
    id("com.modrinth.minotaur") version "2.+"
}

defaultTasks("build")

rootProject.group = project.property("group") as String
rootProject.version = project.property("version") as String
rootProject.description = project.property("description") as String

val combine = tasks.register<Jar>("combine") {
    mustRunAfter("build")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val jarFiles = subprojects.flatMap { subproject ->
        files(subproject.layout.buildDirectory.file("libs/${rootProject.name}-${subproject.name}-${subproject.version}.jar").get())
    }.filter { it.name != "MANIFEST.MF" }.map { file ->
        if (file.isDirectory) file else zipTree(file)
    }

    from(jarFiles)
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set("DaNtdRka")
    versionNumber.set(project.version as String)
    versionType.set("release")
    uploadFile.set(tasks.jar)
    gameVersions.addAll("1.21.8")
    loaders.addAll("fabric", "paper", "spigot", "bukkit", "purpur", "neoforge")
    syncBodyFrom.set(rootProject.file("README.md").readText())
    changelog.set(rootProject.file("CHANGELOG.md").readText())
}

tasks.named("modrinth") {
    dependsOn(tasks.named("combine"))
}

allprojects {
    listOf(
        ":fabric",
        ":paper",
        ":neoforged",
        ":common"
    ).forEach {
        project(it) {
            version = rootProject.version
            group = "${rootProject.group}.${this.name}"

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

            base {
                archivesName.set("${rootProject.name}-${project.name}")
            }

            if (this.name != "common") {
                dependencies {
                    compileOnly(project(":common"))
                }
            }

        }
    }
}

tasks {
    assemble {
        subprojects.forEach { project ->
            dependsOn(":${project.name}:clean")
            dependsOn(":${project.name}:processResources")
            dependsOn(":${project.name}:build")
        }

        finalizedBy(combine)
    }
}
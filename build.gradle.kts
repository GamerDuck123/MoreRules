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
            apply(plugin = "modrinth-plugin")
            apply(plugin = "hangar-plugin")
        }

        if (this.name == "fabric") {
            apply(plugin = "fabric-plugin")
            apply(plugin = "modrinth-plugin")
            apply(plugin = "curseforge-plugin")
        }

        if (this.name == "neoforge") {
            apply(plugin = "neoforge-plugin")
            apply(plugin = "modrinth-plugin")
            apply(plugin = "curseforge-plugin")
        }

        if (this.name == "common") {
            apply(plugin = "common-plugin")
        }


        if (this.name == "mixins") {
            apply(plugin = "mixins-plugin")
        }

        base {
            archivesName.set("${rootProject.name}-${name}")
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
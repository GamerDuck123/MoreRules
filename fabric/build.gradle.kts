plugins {
    id("root-plugin")

    id("fabric-loom") version "1.11-SNAPSHOT"
}

group = "${rootProject.group}.fabric"
version = rootProject.version

dependencies {
    compileOnly(project(":common"))

    minecraft("com.mojang:minecraft:${property("minecraft_version")}")

    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
}

base {
    archivesName.set("${rootProject.name}-${project.name}")
}

loom {
//    splitEnvironmentSourceSets()
    accessWidenerPath.set(file("src/main/resources/more-rules.accesswidener"))
//    mods {
//        "more-rules" {
//            sourceSet sourceSets.main
//                    sourceSet sourceSets.client
//        }
//    }

}


tasks {
    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "description" to project.properties["description"],
            "fabricApiVersion" to project.properties["fabric_version"],
            "fabricLoaderVersion" to project.properties["loader_version"],
            "minecraftVersion" to project.properties["minecraft_version"],
            "website" to project.properties["website"],
            "sources" to project.properties["sources"],
            "issues" to project.properties["issues"]
        )

        listOf(
            "fabric.mod.json",
            "more-rules.mixins.json"
        ).forEach {
            filesMatching(it) {
                expand(props)
            }
        }
    }
}
plugins {
    id("root-plugin")

    id("fabric-loom") version "1.3-SNAPSHOT"
}

group = "${rootProject.group}.fabric"
version = rootProject.version

dependencies {
    compileOnly(project(":common"))

    minecraft("com.mojang", "minecraft", project.property("minecraftVersion") as String)

    mappings("net.fabricmc", "yarn", project.property("yarnMappings") as String)

    modImplementation("net.fabricmc", "fabric-loader", project.property("fabricLoaderVersion") as String)
    modImplementation("net.fabricmc.fabric-api", "fabric-api", project.property("fabricApiVersion") as String)
}

base {
    archivesName.set("${rootProject.name}-${project.name}")
}

loom {
    splitEnvironmentSourceSets()
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
            "fabricApiVersion" to project.properties["fabricApiVersion"],
            "fabricLoaderVersion" to project.properties["fabricLoaderVersion"],
            "minecraftVersion" to project.properties["minecraftVersion"],
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
minecraft {
    mappings(mapOf("channel" to project.property("mapping_channel"), "version" to project.property("mapping_version")))
    reobf = false;
//    reobf.set(false)
    copyIdeResources.set(true)

//    runs {
//        configureEach {
//            workingDirectory = project.file("run")
//
//            property("forge.logging.markers", "REGISTRIES")
//            property("forge.logging.console.level", "debug")
//        }
//
//        create("client") {
//            property("forge.enabledGameTestNamespaces", mod_id)
//        }
//
//        create("server") {
//            property("forge.enabledGameTestNamespaces", mod_id)
//            args("--nogui")
//        }
//
//        create("gameTestServer") {
//            property("forge.enabledGameTestNamespaces", mod_id)
//        }
//
//        create("data") {
//            workingDirectory = project.file("run-data")
//            args(
//                "--mod", mod_id,
//                "--all",
//                "--output", file("src/generated/resources/"),
//                "--existing", file("src/main/resources/")
//            )
//        }
//    }
}

mixin {
    add(sourceSets.main.get(), "${project.property("modid")}.refmap.json")
    config("${project.property("modid")}.mixins.json")
}

dependencies {
    minecraft("net.minecraftforge:forge:${project.property("minecraft_version")}-${project.property("forge_version")}")

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")

    implementation("net.sf.jopt-simple:jopt-simple:5.0.4") {
        version {
            strictly("5.0.4")
        }
    }
}

modrinth {
    uploadFile.set(tasks.jar)
}

//tasks.named<ProcessResources>("processResources") {
//    val replaceProperties = mapOf(
//        "minecraft_version" to minecraft_version,
//        "minecraft_version_range" to minecraft_version_range,
//        "forge_version" to forge_version,
//        "forge_version_range" to forge_version_range,
//        "loader_version_range" to loader_version_range,
//        "mod_id" to mod_id,
//        "mod_name" to mod_name,
//        "mod_license" to mod_license,
//        "mod_version" to mod_version,
//        "mod_authors" to mod_authors,
//        "mod_description" to mod_description,
//    )
//
//    inputs.properties(replaceProperties)
//
//    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
//        expand(replaceProperties + mapOf("project" to project))
//    }
//}

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to project.property("modid"),
                "Specification-Vendor" to project.property("author"),
                "Specification-Version" to "1",
                "Implementation-Title" to project.name,
                "Implementation-Version" to archiveVersion.get(),
                "Implementation-Vendor" to project.property("author"),
//                "Implementation-Timestamp" to Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
            )
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

sourceSets.forEach {
    val dir = layout.buildDirectory.dir("sourcesSets/${it.name}").get().asFile
    it.output.setResourcesDir(dir)
    it.java.destinationDirectory.set(dir)
}
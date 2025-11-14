plugins {
    id("com.modrinth.minotaur")
}

modrinth {
    versionNumber.set("${version as String}-${name}")
    loaders.addAll(
        when (name) {
            "fabric" -> listOf("fabric", "babric", "quilt")
            "neoforge" -> listOf("neoforge")
            "sponge" -> listOf("sponge")
            "paper" -> listOf("paper", "purpur")
            "spigot" -> listOf("spigot", "bukkit")
            else -> throw IllegalStateException("Unknown loader $name")
        }
    )

    uploadFile.set(when (project.name) {
        "fabric" -> tasks.named<net.fabricmc.loom.task.RemapJarTask>("remapJar")
        "neoforge" -> tasks.named<Jar>("jar")
        "paper" -> tasks.named<Jar>("jar")
        "spigot" -> tasks.named<Jar>("jar")
        else -> throw IllegalStateException("Unknown loader $name")
    })

    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set(rootProject.property("modrinthID") as String)
    versionType.set(rootProject.property("versionType") as String)
    gameVersions.addAll(rootProject.property("minecraftVersion") as String)
    syncBodyFrom.set(rootProject.file("README.md").readText())
    changelog.set(rootProject.file("CHANGELOG.md").readText())
}
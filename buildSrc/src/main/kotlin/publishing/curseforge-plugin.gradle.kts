plugins {
    id("net.darkhax.curseforgegradle")
}

tasks.register("publishCurseForge", net.darkhax.curseforgegradle.TaskPublishCurseForge::class) {
    apiToken = findProperty("curseforge_token") as String?

    val projectId = findProperty("curseforge_project") as String?

    val mainFile = upload(projectId, tasks.getByName("jar"))
    mainFile.changelog = "The changelog string for this file."
}

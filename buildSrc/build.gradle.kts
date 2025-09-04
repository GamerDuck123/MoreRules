plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net")
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    implementation("com.gradleup.shadow:shadow-gradle-plugin:${rootProject.property("shadow_jar")}")
    implementation("io.papermc.paperweight:paperweight-userdev:${rootProject.property("paper_weight")}")
    implementation("net.neoforged:moddev-gradle:${rootProject.property("neo_forge")}")
    implementation("fabric-loom:fabric-loom.gradle.plugin:${rootProject.property("fabric_loom")}")
    implementation("com.modrinth.minotaur:Minotaur:${rootProject.property("minotaur")}")
    implementation("org.spongepowered:mixingradle:${rootProject.property("sponge")}")
    implementation("net.minecraftforge.gradle:ForgeGradle:${rootProject.property("forge")}")
}
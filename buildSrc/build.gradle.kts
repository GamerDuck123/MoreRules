plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net")
}

dependencies {
    implementation("com.gradleup.shadow:shadow-gradle-plugin:${rootProject.property("shadow_jar")}")
    implementation("io.papermc.paperweight:paperweight-userdev:${rootProject.property("paper_weight")}")
    implementation("net.neoforged:moddev-gradle:${rootProject.property("neo_forge")}")
    implementation("fabric-loom:fabric-loom.gradle.plugin:${rootProject.property("fabric_loom")}")
}
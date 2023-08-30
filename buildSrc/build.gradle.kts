plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.johnrengelman", "shadow", "8.1.1")

    implementation("io.papermc.paperweight", "paperweight-userdev", "1.5.5")
}
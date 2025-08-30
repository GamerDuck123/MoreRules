plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.gradleup.shadow", "shadow-gradle-plugin", "9.1.0")

    implementation("io.papermc.paperweight", "paperweight-userdev", "2.0.0-beta.18")
}
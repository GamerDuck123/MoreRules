plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.gradleup.shadow:shadow-gradle-plugin:${rootProject.property("shadow_jar")}")
    implementation("io.papermc.paperweight:paperweight-userdev:${rootProject.property("paper_weight")}")
}
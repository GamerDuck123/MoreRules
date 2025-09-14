plugins {
    id("root-plugin")
}

tasks.withType<JavaCompile>().configureEach {
    enabled = false
}
tasks.named("jar").configure {
    enabled = false
}
tasks.named("build").configure {
    enabled = false
}
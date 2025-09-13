dependencies {
    // Only needed for annotations/compile-time
    compileOnly("org.spongepowered:mixin:0.8.5")
    compileOnly(project(":common"))

    // For annotation processor (validates your mixins at build time)
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

neoForge {
    neoFormVersion = "1.21.8-20250717.133445"
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

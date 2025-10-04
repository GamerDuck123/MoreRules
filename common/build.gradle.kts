dependencies {
    compileOnly(libs.gson)

    compileOnly("org.spongepowered:mixin:0.8.5")
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

neoForge {
    neoFormVersion = "1.21.8-20250717.133445"
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

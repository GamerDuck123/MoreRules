dependencies {
    compileOnly(libs.gson)

    compileOnly(libs.mixin)
    annotationProcessor("${libs.mixin.get()}:processor")

    implementation("io.github.llamalad7:mixinextras-common:0.5.0")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.5.0")
}

neoForge {
    neoFormVersion = libs.versions.neoform.get()
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

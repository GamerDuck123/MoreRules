dependencies {
    compileOnly(libs.gson)

    compileOnly(libs.mixin)
    annotationProcessor("${libs.mixin.get()}:processor")
}

neoForge {
    neoFormVersion = libs.versions.neoform.get()
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

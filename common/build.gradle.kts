plugins {
    id("root-plugin")
}

dependencies {
    compileOnly(libs.gson)
}

tasks {
    assemble {
//        dependsOn(shadowJar)
        doLast {
            delete(fileTree(baseDir = "$buildDir").include("**/*-dev*.jar"))
        }
    }

//    shadowJar {
//        archiveBaseName.set("${rootProject.name}-${project.name}")
//        archiveClassifier.set("")
//        mergeServiceFiles()

//        listOf(
//            "net.kyori",
//            "org.bstats",
//            "org.simpleyaml",
//            "org.yaml.snakeyaml"
//        ).forEach {
//            relocate(it, "libs.$it")
//        }
//    }
}
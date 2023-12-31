plugins {
    id("root-plugin")
}

group = "${rootProject.group}.common"
version = rootProject.version
dependencies {
    compileOnly("com.google.code.gson:gson:2.10.1")
}

base {
    archivesName.set("${rootProject.name}-${project.name}")
}

tasks {
    assemble {
        dependsOn(shadowJar)
        doLast {
            delete(fileTree(baseDir = "$buildDir").include("**/*-dev*.jar"))
        }
    }

    shadowJar {
        archiveBaseName.set("${rootProject.name}-${project.name}")
        archiveClassifier.set("")
        mergeServiceFiles()

//        listOf(
//            "net.kyori",
//            "org.bstats",
//            "org.simpleyaml",
//            "org.yaml.snakeyaml"
//        ).forEach {
//            relocate(it, "libs.$it")
//        }
    }
}
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.exclude

dependencies {
    compileOnly(libs.spigot)
    compileOnly(libs.brigadier)
    compileOnly(libs.gson)
    implementation("me.lucko:commodore:2.2")
}

modrinth {
    uploadFile.set(tasks.shadowJar)
}

tasks.register<Copy>("copyCommonSources") {
    from("$rootDir/common/src/main/java") {
        exclude("me/gamerduck/rules/mixin/**")
        into("common/java")
    }
    from("$rootDir/common/src/main/resources") {
        exclude("META-INF/**")
        exclude("templates/**")
        exclude("morerules.accesswidener")
        exclude("morerules.mixins.json")
        into("common/resources")
    }

    from("$rootDir/common/src/main/resources/templates") {
        include("morerules.properties")      // only copy that file
        into("common/resources")      // place it directly into common/resources
        includeEmptyDirs = false

        filesMatching("**/morerules.properties") {
            expand(mapOf(
                "default_path" to "plugins/MoreRules/storage",
            ))
        }
    }

    into("${layout.buildDirectory}/generated/sources")
}

sourceSets {
    main {
        java {
            srcDir("${layout.buildDirectory}/generated/sources/common/java")
        }
        resources {
            srcDir("${layout.buildDirectory}/generated/sources/common/resources")
        }
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn("copyCommonSources")
}

tasks {
    processResources {
        dependsOn("copyCommonSources")
        val props = mapOf(
            "name" to rootProject.property("modid"),
            "group" to project.group,
            "version" to project.version,
            "description" to rootProject.property("description") as String,
            "apiVersion" to libs.versions.minecraft.get()
        )

        from("src/main/templates") {
            listOf(
                "plugin.yml",
            ).forEach {
                filesMatching(it) {
                    expand(props)
                }
            }
        }
        into(layout.buildDirectory.dir("src/main/resources"))
    }
    build {
        dependsOn("shadowJar")
    }
    shadowJar {
        dependencies {
            exclude(dependency("com.mojang:brigadier"))
        }

        /* vvv Replace with the package of your plugin vvv */
        relocate("me.lucko.commodore", "me.gamerduck.rules.bukkit.commodore")
        archiveClassifier.set("")
    }
}

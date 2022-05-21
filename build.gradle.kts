@file:Suppress("PropertyName")

val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project

val mod_version: String by project
val maven_group: String by project

plugins {
    id("fabric-loom") version "0.11-SNAPSHOT"
    id("maven-publish")
}

version = mod_version
group = maven_group

dependencies {
    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings("net.fabricmc:yarn:${yarn_mappings}:v2")

    modImplementation("net.fabricmc:fabric-loader:${loader_version}")
}

java {
    withSourcesJar()
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching(listOf("fabric.mod.json")) {
            expand(mapOf("version" to project.version))
        }
    }

    "compileJava"(JavaCompile::class) {
        options.release.set(17)
    }

    "jar"(Jar::class) {
        from("LICENSE") {
            rename { "${it}_Toasts" }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}

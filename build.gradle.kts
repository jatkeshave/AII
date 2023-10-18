plugins {
    id("jatkeshave-aai")
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("org.jetbrains.intellij") version "1.16.0"
}

//apply(from = "AAIPlugin")

group = "com.jatkeshave"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    // Same IntelliJ IDEA version (2019.1.4) as target 3.5 Android Studio:
    version.set("2022.3.3")

    // Use IntelliJ IDEA CE because it's the basis of the IntelliJ Platform:
    type.set("IC")

    // Require the Android plugin (Gradle will choose the correct version):
    plugins.set(listOf("android"))

//    localPath.set(extra["androidStudioPath"] as String)
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("223.8836.35")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    runIde {
        ideDir.set(file(project.extra["androidStudioPath"] as String))
        autoReloadPlugins.set(true)
    }
}

afterEvaluate {
    println("-------------------------------All properties--------------------------------")
    extra.properties.forEach(::println)
}

dependencies {
    implementation("io.arrow-kt:arrow-core:1.2.0")
}

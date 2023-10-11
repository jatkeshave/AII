import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("aai-plugin") {
            id = "jatkeshave-aai"
            implementationClass = "com.jatkeshave.aai.gradle.AAIPlugin"
        }
    }
}

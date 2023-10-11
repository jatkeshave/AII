package com.jatkeshave.aai.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import java.util.Properties

class AAIPlugin : Plugin<Project> {
    private fun loadLocalProperties(project: Project): Unit = project.fileTree(project.projectDir)
            .apply {
                include("*.properties")
                exclude("gradle.properties")
            }
            .map { propFile -> propFile.inputStream().use { Properties().apply { load(it) } } }
            .forEach { props ->
                for (propName in props.keys.filterIsInstance<String>()) {
                    if (!project.hasProperty(propName))
                        project.extra.set(propName, props.getProperty(propName))
                }
            }

    override fun apply(target: Project) {
        loadLocalProperties(target)
    }
}

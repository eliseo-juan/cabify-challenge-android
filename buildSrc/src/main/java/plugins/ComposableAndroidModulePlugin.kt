package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import dev.eliseo.cabify.buildsrc.Version

class ComposableAndroidModulePlugin : Plugin<Project>, AndroidExtensionProvider {

    override fun apply(project: Project) {

        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
        }

        getAndroidExtension(project)?.apply {
            buildFeatures.apply {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = Version.compose
            }
        }
    }
}

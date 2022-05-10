package plugins

import ConfigData
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

open class AndroidModulePlugin : Plugin<Project>, AndroidExtensionProvider {

    override fun apply(project: Project) {

        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
            apply("dagger.hilt.android.plugin")
        }

        getAndroidExtension(project)?.apply {
            compileSdkVersion = ConfigData.compileSdkVersion
            buildToolsVersion = ConfigData.buildToolsVersion

            defaultConfig {
                minSdk = ConfigData.minSdkVersion
                targetSdk = ConfigData.targetSdkVersion
                versionCode = ConfigData.versionCode
                versionName = ConfigData.versionName

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                getByName("debug") {
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            project.tasks.withType(KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        project.addHiltDependencies()
    }
}

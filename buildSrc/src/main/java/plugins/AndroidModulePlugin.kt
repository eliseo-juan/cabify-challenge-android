package plugins

import ConfigData
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidModulePlugin : Plugin<Project> {

    companion object {
        const val implementation = "implementation"
    }

    override fun apply(project: Project) {

        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
        }

        val androidExtension = project.extensions.getByName("android") as? BaseExtension
        androidExtension?.apply {
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

    }
}

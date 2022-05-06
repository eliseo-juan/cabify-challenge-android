import dev.eliseo.cabify.buildsrc.Version
import dev.eliseo.cabify.buildsrc.Module
import plugins.addCoreDependencies
import plugins.addComposeDependencies
import plugins.addAndroidxLifecycleDependencies
import plugins.addCoilDependencies
import plugins.addHiltDependencies
import plugins.addTestDependencies

plugins {
    id("com.android.application")
    id("my-android-plugin")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = "dev.eliseo.cabify.store"
    }

    buildFeatures.apply {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose
    }
}

addCoreDependencies()
addHiltDependencies()
addComposeDependencies()
addAndroidxLifecycleDependencies()
addCoilDependencies()

addTestDependencies()

dependencies {
    implementation(project(Module.libBase))
    implementation(project(Module.Core.domain))
    implementation(project(Module.Core.data))
    implementation(project(Module.Feature.store))
}
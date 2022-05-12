import dev.eliseo.cabify.buildsrc.Module
import plugins.addCoreDependencies
import plugins.addComposeDependencies
import plugins.addAndroidxLifecycleDependencies
import plugins.addCoilDependencies
import plugins.addHiltDependencies
import plugins.addTestDependencies

plugins {
    id("com.android.application")
    id("plugin_library")
    id("plugin_library_compose")
}

android {
    defaultConfig {
        applicationId = "dev.eliseo.cabify.store"
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
    implementation(project(Module.Core.navigation))
    implementation(project(Module.Core.presentation))
    implementation(project(Module.Core.ds))
    implementation(project(Module.Feature.store))

}
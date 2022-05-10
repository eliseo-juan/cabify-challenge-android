import dev.eliseo.cabify.buildsrc.Module
import plugins.addCoreDependencies
import plugins.addHiltDependencies
import plugins.addComposeDependencies
import plugins.addAndroidxLifecycleDependencies
import plugins.addCoilDependencies
import plugins.addTestDependencies

plugins {
    id("com.android.library")
    id("plugin_library")
    id("plugin_library_compose")
}

addCoreDependencies()
addHiltDependencies()
addComposeDependencies()
addAndroidxLifecycleDependencies()
addCoilDependencies()

addTestDependencies()

dependencies {
    implementation(project(Module.libBase))
    implementation(project(Module.Core.ds))
    implementation(project(Module.Core.domain))
    implementation(project(Module.Core.navigation))
}
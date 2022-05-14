import dev.eliseo.cabify.buildsrc.Module
import plugins.*

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
    implementation(project(Module.Core.ds))
    implementation(project(Module.Core.domain))
    implementation(project(Module.Core.navigation))
    implementation(project(Module.Core.presentation))
}
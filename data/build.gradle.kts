import dev.eliseo.cabify.buildsrc.Module
import plugins.*

plugins {
    id("com.android.library")
    id("plugin_library")
}

addCoreDependencies()
addHiltDependencies()
addCoroutinesDependencies()
addDatastoreDependencies()
addGsonDependencies()
addRetrofitDependencies()
addTestDependencies()

dependencies {
    implementation(project(Module.Core.domain))
}
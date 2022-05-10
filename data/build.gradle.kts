import dev.eliseo.cabify.buildsrc.Module
import plugins.addCoreDependencies
import plugins.addHiltDependencies
import plugins.addCoroutinesDependencies
import plugins.addDatastoreDependencies
import plugins.addGsonDependencies
import plugins.addRetrofitDependencies
import plugins.addTestDependencies

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
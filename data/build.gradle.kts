import dev.eliseo.cabify.buildsrc.Version
import dev.eliseo.cabify.buildsrc.Module
import plugins.addCoreDependencies
import plugins.addHiltDependencies
import plugins.addCoroutinesDependencies
import plugins.addDatastoreDependencies
import plugins.addMoshiDependencies
import plugins.addRetrofitDependencies
import plugins.addTestDependencies

plugins {
    id("com.android.library")
    id("my-android-plugin")
}

addCoreDependencies()
addHiltDependencies()
addCoroutinesDependencies()
addDatastoreDependencies()
addMoshiDependencies()
addRetrofitDependencies()
addTestDependencies()

dependencies {
    implementation(project(Module.Core.domain))
}
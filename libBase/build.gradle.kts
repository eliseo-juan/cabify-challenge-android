import dev.eliseo.cabify.buildsrc.Version
import dev.eliseo.cabify.buildsrc.Module
import plugins.addCoreDependencies
import plugins.addHiltDependencies
import plugins.addComposeDependencies
import plugins.addAndroidxLifecycleDependencies
import plugins.addTestDependencies

plugins {
    id("com.android.library")
    id("my-android-plugin")
}

addCoreDependencies()
addHiltDependencies()
addComposeDependencies()
addAndroidxLifecycleDependencies()

addTestDependencies()
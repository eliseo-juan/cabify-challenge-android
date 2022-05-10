import plugins.addCoreDependencies
import plugins.addHiltDependencies
import plugins.addComposeDependencies
import plugins.addAndroidxLifecycleDependencies
import plugins.addTestDependencies
import plugins.addCoroutinesDependencies

plugins {
    id("com.android.library")
    id("plugin_library")
    id("plugin_library_compose")
}

addCoreDependencies()
addHiltDependencies()
addComposeDependencies()
addAndroidxLifecycleDependencies()
addCoroutinesDependencies()

addTestDependencies()
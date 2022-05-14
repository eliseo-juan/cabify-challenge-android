import plugins.addCoroutinesDependencies
import plugins.addHiltDependencies
import plugins.addTestDependencies

plugins {
    id("com.android.library")
    id("plugin_library")
}

addHiltDependencies()
addCoroutinesDependencies()

addTestDependencies()

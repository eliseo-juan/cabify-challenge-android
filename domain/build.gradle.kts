import plugins.addCoroutinesDependencies
import plugins.addHiltDependencies

plugins {
    id("com.android.library")
    id("plugin_library")
}

addHiltDependencies()
addCoroutinesDependencies()

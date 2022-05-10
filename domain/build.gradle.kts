import plugins.addHiltDependencies
import plugins.addCoroutinesDependencies

plugins {
    id("com.android.library")
    id("plugin_library")
}

addHiltDependencies()
addCoroutinesDependencies()

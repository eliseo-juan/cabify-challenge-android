import plugins.addComposeDependencies
import plugins.addCoreDependencies
import plugins.addHiltDependencies

plugins {
    id("com.android.library")
    id("plugin_library")
    id("plugin_library_compose")
}

addCoreDependencies()
addHiltDependencies()
addComposeDependencies()
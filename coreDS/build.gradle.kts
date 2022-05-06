import plugins.addCoreDependencies
import plugins.addHiltDependencies
import plugins.addComposeDependencies

plugins {
    id("com.android.library")
    id("my-android-plugin")
}

addCoreDependencies()
addHiltDependencies()
addComposeDependencies()
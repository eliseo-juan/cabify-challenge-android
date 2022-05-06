import plugins.addHiltDependencies
import plugins.addCoroutinesDependencies

plugins {
    id("com.android.library")
    id("my-android-plugin")
}

addCoroutinesDependencies()
addCoroutinesDependencies()

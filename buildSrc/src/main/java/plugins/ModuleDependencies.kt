package plugins

import dev.eliseo.cabify.buildsrc.Version
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

private fun DependencyHandlerScope.implementation(dependency: String) {
    add("implementation", dependency)
}

private fun DependencyHandlerScope.implementation(dependency: Dependency) {
    add("implementation", dependency)
}

private fun DependencyHandlerScope.kapt(dependency: String) {
    add("kapt", dependency)
}

private fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

private fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

private fun DependencyHandlerScope.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun Project.addCoreDependencies() {
    dependencies {
        implementation("androidx.core:core-ktx:1.7.0")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")
    }
}

fun Project.addCoroutinesDependencies() {
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    }
}

fun Project.addComposeDependencies() {
    dependencies {
        implementation("androidx.compose.ui:ui:${Version.compose}")
        implementation("androidx.compose.material:material:${Version.compose}")
        implementation("androidx.compose.ui:ui-tooling-preview:${Version.compose}")
        implementation("androidx.activity:activity-compose:1.4.0")
        implementation("androidx.navigation:navigation-compose:2.4.2")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
        implementation("androidx.compose.material:material-icons-extended:${Version.compose}")
        implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.7-alpha")
        debugImplementation("androidx.compose.ui:ui-tooling:${Version.compose}")
    }
}

fun Project.addAndroidxLifecycleDependencies() {
    dependencies {
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycle}")
    }
}

fun Project.addCoilDependencies() {
    dependencies {
        implementation("io.coil-kt:coil:2.0.0-rc03")
        implementation("io.coil-kt:coil-compose:2.0.0-rc03")
    }
}

fun Project.addHiltDependencies() {
    dependencies {
        implementation("com.google.dagger:hilt-android:${Version.hilt}")
        kapt("com.google.dagger:hilt-android-compiler:${Version.hilt}")
    }
}

fun Project.addDatastoreDependencies() {
    dependencies {
        implementation("androidx.datastore:datastore:1.0.0")
        implementation("androidx.datastore:datastore-preferences:1.0.0")
    }
}

fun Project.addGsonDependencies() {
    dependencies {
        implementation("com.google.code.gson:gson:2.9.0")
    }
}

fun Project.addRetrofitDependencies() {
    dependencies {
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    }
}

fun Project.addTestDependencies() {
    dependencies {
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Version.compose}")
    }
}
plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("plugin_library") {
            id = "plugin_library"
            implementationClass = "plugins.AndroidModulePlugin"
        }
        register("plugin_library_compose") {
            id = "plugin_library_compose"
            implementationClass = "plugins.ComposableAndroidModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:7.1.2")
    implementation(kotlin("gradle-plugin", "1.6.10"))
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
}
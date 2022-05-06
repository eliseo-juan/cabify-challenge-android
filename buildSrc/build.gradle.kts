plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("my-android-plugin") {
            id = "my-android-plugin"
            implementationClass = "plugins.AndroidModulePlugin"
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
plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false // Make Kotlin Multiplatform available
    alias(libs.plugins.kotlinJvm) apply false          // Make Kotlin JVM available
    alias(libs.plugins.androidLibrary) apply false     // Make Android Library plugin available
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

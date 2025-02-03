plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    alias(libs.plugins.serialization)
}

kotlin {
    jvm()
    js(IR) {
        browser()
        nodejs()
    }

    applyDefaultHierarchyTemplate()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    androidTarget() // Correct Android target configuration

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.serializationCore)
                implementation(libs.serializationJson)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.apiclient"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

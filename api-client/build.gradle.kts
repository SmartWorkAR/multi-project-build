plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvm()
//    js(IR) {
//        browser()
//        nodejs()
//    }

    // Apply the default iOS and other Apple targets
    applyDefaultHierarchyTemplate() // Ensures all iOS targets are configured
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // macOS targets
    macosX64()
    macosArm64()

    // Use the new Android DSL
    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":model"))
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.cio)
                //implementation(libs.ktor.client.js)
                implementation(libs.ktorClientSerialization)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.content.negotiation)
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


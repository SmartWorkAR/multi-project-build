plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvm()
    js(IR) {
        browser()
        nodejs()
    }

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
                implementation(libs.ktorClientSerialization)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.content.negotiation)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutines.core)
                implementation(libs.coroutines.test)
                implementation(libs.ktor.client.mock)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio) // CIO engine for JVM
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.logback.classic)  // Only for JVM tests
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
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

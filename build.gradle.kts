import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

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

subprojects {
    tasks.withType<Test> {
        testLogging {
            events("passed", "skipped", "failed") // Log events for all test outcomes
            showStandardStreams = true
        }
    }
    tasks.withType(KotlinNativeTest::class).configureEach {
        testLogging {
            showStandardStreams = true
            events("passed", "failed", "skipped", "standardOut", "standardError")
        }
    }
}

tasks.register<TestReport>("testReport") {
    destinationDirectory.set(layout.buildDirectory.dir("reports/tests"))

    // Include results from all subprojects
    subprojects.forEach { subproject ->
        val testTask = subproject.tasks.findByName("test")
        if (testTask is Test) {
            reportOn(testTask.binaryResultsDirectory)
        }
    }
}


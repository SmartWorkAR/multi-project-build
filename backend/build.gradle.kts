plugins {
    alias(libs.plugins.kotlinJvm)
    application
}

application {
    mainClass.set("com.example.backend.MainKt")
}

dependencies {
    implementation(project(":model"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktorClientSerialization)
    implementation(libs.serializationJson)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.logback.classic)
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

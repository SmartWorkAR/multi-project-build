package com.example.backend

import com.example.model.DataModel
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.EngineMain
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json


fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; ignoreUnknownKeys = true })
    }
    routing {
        get("/") {
            val data = DataModel(id = 1, name = "Example")
            call.respond(HttpStatusCode.OK, data)
        }
    }
}
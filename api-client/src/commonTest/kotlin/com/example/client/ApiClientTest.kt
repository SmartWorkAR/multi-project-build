package com.example.client

import com.example.model.DataModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.Platform
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest // For multiplatform-compatible coroutine testing
import kotlinx.serialization.json.Json

expect fun getPlatformName(): String

class ApiClientTest {

    private fun createMockHttpClient(): HttpClient {
        val mockEngine = MockEngine { _ ->
            respond(
                content = """{"id":1,"name":"Example"}""",
                status = HttpStatusCode.OK,
                headers = headersOf("Content-Type", ContentType.Application.Json.toString())
            )
        }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    @Test
    fun `test fetchData returns correct DataModel`() = runTest {

        println("Running in ${getPlatformName()}")
        val mockHttpClient = createMockHttpClient()
        val apiClient = ApiClient(mockHttpClient)

        // Fetch data and verify
        val result = apiClient.fetchData()
        assertEquals(DataModel(1, "Example"), result)
    }
}
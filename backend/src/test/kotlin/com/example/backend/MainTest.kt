package com.example.backend

import com.example.model.DataModel
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.json.Json
import kotlin.test.Test

class MainTest {
    @Test
    fun `test GET root endpoint with deserialization`() = testApplication {
        application {
            module()
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        val responseValue: DataModel = Json.decodeFromString(response.bodyAsText())

        assertEquals(responseValue, DataModel(1, "Example"))
    }
}
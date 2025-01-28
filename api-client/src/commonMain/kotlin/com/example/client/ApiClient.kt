package com.example.client

import com.example.model.DataModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiClient(private val client: HttpClient) {
    suspend fun fetchData(): DataModel {
        return client.get("http://localhost:8080/").body()
    }
}

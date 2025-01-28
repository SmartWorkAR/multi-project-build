package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class DataModel(
    val id: Int,
    val name: String
)

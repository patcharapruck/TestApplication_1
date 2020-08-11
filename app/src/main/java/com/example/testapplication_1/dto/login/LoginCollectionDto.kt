package com.example.testapplication_1.dto.login

import com.google.gson.annotations.SerializedName

data class LoginCollectionDto(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: LoginItemDto?

)


package com.example.testapplication_1.dto.register

import com.google.gson.annotations.SerializedName

data class RegisterCollectionDto(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String

)


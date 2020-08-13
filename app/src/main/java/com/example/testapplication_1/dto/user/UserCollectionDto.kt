package com.example.testapplication_1.dto.user

import com.google.gson.annotations.SerializedName

data class UserCollectionDto(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UserItemDto

)


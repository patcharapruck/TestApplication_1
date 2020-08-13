package com.example.testapplication_1.dto.user

import com.example.testapplication_1.dto.career.CareerListItemDto
import com.google.gson.annotations.SerializedName

data class CareerCollectionDto(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CareerListItemDto?

)


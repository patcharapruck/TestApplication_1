package com.example.testapplication_1.dto.career

import com.google.gson.annotations.SerializedName

data class ResCareerCollectionDto(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String

)


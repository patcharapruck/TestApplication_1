package com.example.testapplication_1.dto.career

import com.google.gson.annotations.SerializedName
import java.util.*


data class CareerListItemDto(
    @SerializedName("USER_CAREER_ID") val userCareerID: Int?,
    @SerializedName("USER_CAREER_NAME") val userCareername: String?,
    @SerializedName("USER_CAREER_SALARY") val salary: Int?,
    @SerializedName("USER_ID") val user_id: Int?
)


package com.example.testapplication_1.dto.login

import com.google.gson.annotations.SerializedName
import java.util.*


data class LoginItemDto(
    @SerializedName("USER_ID") val userID: Int?,
    @SerializedName("USER_NAME") val name: String?,
    @SerializedName("USER_BIRTH_DATE") val date: Date?,
    @SerializedName("USER_MOBILE_PHONE_NUMBER") val phone: String?,
    @SerializedName("LOGIN_ID") val loginID: Int?
)


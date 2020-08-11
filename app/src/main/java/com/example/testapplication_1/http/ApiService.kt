package com.example.testapplication_1.http

import com.example.testapplication_1.dto.login.LoginCollectionDto
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*


interface ApiService {


    @GET("user/login")
    fun loginAPI(
        @Query("LOGIN_USERNAME") username: String?,
        @Query("LOGIN_PASSWORD") password: String?
    ): Call<LoginCollectionDto>

    @POST("user")
    fun registerAPI(
        @Body username: String?,
        @Query("LOGIN_PASSWORD") password: String?
    ): Call<LoginCollectionDto>

}
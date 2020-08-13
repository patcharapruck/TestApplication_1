package com.example.testapplication_1.http

import com.example.testapplication_1.dto.career.ResCareerCollectionDto
import com.example.testapplication_1.dto.login.LoginCollectionDto
import com.example.testapplication_1.dto.register.RegisterCollectionDto
import com.example.testapplication_1.dto.user.CareerCollectionDto
import com.example.testapplication_1.dto.user.CareerListCollectionDto
import com.example.testapplication_1.dto.user.UserCollectionDto
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @GET("user/login")
    fun loginAPI(
        @Query("LOGIN_USERNAME") username: String?,
        @Query("LOGIN_PASSWORD") password: String?
    ): Call<LoginCollectionDto>

    @POST("user")
    fun registerAPI(
        @Body json: RequestBody
    ): Call<RegisterCollectionDto>

    @GET("user")
    fun getUser(
        @Query("id") id: Int?): Call<UserCollectionDto>

    @GET("user/career/list")
    fun getCareerList(
        @Query("id") id: Int?,
        @Query("text") text: String
    ): Call<CareerListCollectionDto>

    @GET("user/career")
    fun getCareer(
        @Query("id") id: Int?): Call<CareerCollectionDto>

    @POST("user/career")
    fun careerAPI(
        @Body json: RequestBody
    ): Call<ResCareerCollectionDto>


}
package com.example.testapplication_1.singleton

import android.content.Context
import com.example.testapplication_1.dto.login.LoginCollectionDto


class LoginManager private constructor(){

    private var loginDto: LoginCollectionDto? = null

    companion object{

        private var instance: LoginManager? = null

        @Synchronized
        fun getInstance(): LoginManager? {
            if (instance == null) instance = LoginManager()
            return instance
        }

    }

    fun getLoginDto(): LoginCollectionDto? {
        return loginDto
    }

    fun setLoginDto(dto: LoginCollectionDto?) {
        this.loginDto = dto
    }

}
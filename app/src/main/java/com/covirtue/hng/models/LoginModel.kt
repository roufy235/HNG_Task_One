package com.covirtue.hng.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("username")
    val username : String = "",
    @SerializedName("email")
    val email : String = "",
    @SerializedName("password")
    val password : String = ""
)
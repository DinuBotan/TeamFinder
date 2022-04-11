package com.project.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    // We map the field with the given name from the API (ex: id Category) to a name defined by us (ex: id)
    // with the help of Retrofit
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class User(
    val userEmail: String,
    val userPassword: String
)
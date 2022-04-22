package com.project.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    // We map the field with the given name from the API (ex: id Category) to a name defined by us (ex: id)
    // with the help of Retrofit
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("location") val location: String,
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("interests") val interests: ArrayList<String>,
    @SerializedName("teams") val teams: ArrayList<String>,
    @SerializedName("imageId") val imageId: Int
)

data class User(
    val userEmail: String,
    val userPassword: String,
    val name: String,
    val location: String,
    val country: String,
    val city: String,
    val interests: ArrayList<String>?,
    val teams: ArrayList<String>?,
    val imageId: Int
)
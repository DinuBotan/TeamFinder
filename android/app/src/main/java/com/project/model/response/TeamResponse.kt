package com.project.model.response

import com.google.gson.annotations.SerializedName

data class TeamsResponse(val teams: List<TeamResponse>)

// Gson deserialization:
// Transform JSON to data classes

data class TeamResponse(
    // We map the field with the given name from the API (ex: id Category) to a name defined by us (ex: id)
    // with the help of Retrofit
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Int
//    @SerializedName("strCategoryThumb") val imageUrl: String
)
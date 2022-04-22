package com.project.model.response

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class TeamsResponse(val teams: List<TeamResponse>)

data class MessagesResponse(val messages: List<MessageResponse>)

// Gson deserialization:
// Transform JSON to data classes

data class TeamResponse(
    // We map the field with the given name from the API (ex: id Category) to a name defined by us (ex: id)
    // with the help of Retrofit
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Int,
    @SerializedName("imageId") val imageId: Int
//    @SerializedName("strCategoryThumb") val imageUrl: String
)

data class NewTeam(
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Int,
    @SerializedName("imageId") val imageId: Int
)

data class MessageResponse(
    @SerializedName("authorId") val authorId: String,
    @SerializedName("content") val content: String,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("chatRoomId") val chatRoomId: String,
)
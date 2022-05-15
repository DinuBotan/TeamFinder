package com.project.model.response

import android.net.Uri
import com.google.gson.annotations.SerializedName
import com.project.teamfinder.R

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
    @SerializedName("imageId") val imageId: Int,
    @SerializedName("members") val members: ArrayList<String>,
    @SerializedName("category") val category: String,
    @SerializedName("creationDate") val creationDate: String,
    @SerializedName("location") val location: String,
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("language") val language: String,

//    @SerializedName("strCategoryThumb") val imageUrl: String
)

data class NewTeam(
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Int,
    @SerializedName("imageId") val imageId: Int,
    @SerializedName("category") val category: String,
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("language") val language: String,
)

data class MessageResponse(
    @SerializedName("authorId") val authorId: String,
    @SerializedName("content") val content: String,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("chatRoomId") val chatRoomId: String,
)

class ImagePicker {
     public fun getImage(teamNum: Int): Int {
        var imageMap: HashMap<Int, Int> = HashMap()
        imageMap.put(1, R.drawable.team1)
        imageMap.put(2, R.drawable.team2)
        if(imageMap.get(teamNum) != null) {
            return imageMap.get(teamNum)!!
        }
        return R.drawable.team1
    }
}
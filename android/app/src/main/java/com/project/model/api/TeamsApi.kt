package com.project.model.api

import com.project.model.response.*
import com.project.teamfinder.ui.conversation.Message
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class TeamsWebService {

    private lateinit var api: TeamsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.3:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TeamsApi::class.java)
    }

    suspend fun getTeams(): TeamsResponse {
        return api.getTeams()
    }

    suspend fun getTeamById(id: String): TeamResponse {
        return api.getTeamById(id)
    }

    suspend fun getMessagesById(teamId: String): MessagesResponse {
        return api.getMessagesById(teamId)
    }

    suspend fun login(user: User): UserResponse {
        return api.login(user)
    }

    interface TeamsApi {
        @GET("teams")
        suspend fun getTeams(): TeamsResponse

        @GET("teams/{id}")
        suspend fun getTeamById(@Path("id") id: String): TeamResponse

        @GET("messages/{teamId}")
        suspend fun getMessagesById(@Path("teamId") teamId: String): MessagesResponse

        @POST("users/login")
        suspend fun login(@Body user: User):UserResponse
    }
}
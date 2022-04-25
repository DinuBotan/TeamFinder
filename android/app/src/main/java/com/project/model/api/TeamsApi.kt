package com.project.model.api

import com.project.model.response.*
import com.project.teamfinder.ui.conversation.Message
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class TeamsWebService {

    private lateinit var api: TeamsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.249:3001/")
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

    suspend fun getUserById(userId: String): UserResponse {
        return api.getUserById(userId)
    }

    suspend fun updateUserById(userId: String, user: UserResponse) {
        return api.updateUserById(userId, user)
    }

    suspend fun createTeam(newTeam: NewTeam) {
        return api.createTeam(newTeam)
    }

    suspend fun searchTeams(teamName: String): TeamsResponse {
        return api.searchTeams(teamName)
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

        @GET("users/{userId}")
        suspend fun getUserById(@Path("userId") userId: String): UserResponse

        @PATCH("users/{userId}")
        suspend fun updateUserById(@Path("userId") userId: String, @Body user: UserResponse)

        @POST("teams")
        suspend fun createTeam(@Body newTeam: NewTeam)

        @GET("teams/search")
        suspend fun searchTeams(@Query("name") name: String): TeamsResponse
    }
}
package com.project.model.api

import com.project.model.response.TeamResponse
import com.project.model.response.TeamsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class TeamsWebService {

    private lateinit var api: TeamsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.34:3001/")
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

    interface TeamsApi {
        @GET("teams")
        suspend fun getTeams(): TeamsResponse

        @GET("teams/{id}")
        suspend fun getTeamById(@Path("id") id: String): TeamResponse
    }
}
package com.project.model

import com.project.model.api.TeamsWebService
import com.project.model.response.TeamResponse
import com.project.model.response.TeamsResponse

class TeamsRepository(private val webService: TeamsWebService = TeamsWebService()) {
    suspend fun getTeams(): TeamsResponse {
        return webService.getTeams()
    }

    suspend fun getTeamById(id: String): TeamResponse {
        return webService.getTeamById(id)
    }
}
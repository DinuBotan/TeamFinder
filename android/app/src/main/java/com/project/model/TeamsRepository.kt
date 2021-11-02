package com.project.model

import com.project.model.api.SocketHandler
import com.project.model.api.TeamsWebService
import com.project.model.response.TeamsResponse

class TeamsRepository(private val webService: TeamsWebService = TeamsWebService()) {
    suspend fun getTeams(): TeamsResponse {
        return webService.getTeams()
    }

    fun initializeSocket() {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }
}
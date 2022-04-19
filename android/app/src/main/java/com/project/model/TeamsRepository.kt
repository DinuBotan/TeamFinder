package com.project.model

import com.project.model.api.SocketHandler
import com.project.model.api.TeamsWebService
import com.project.model.response.TeamsResponse
import com.project.model.response.UserResponse

class TeamsRepository(private val webService: TeamsWebService = TeamsWebService()) {
    suspend fun getTeams(): TeamsResponse {
        return webService.getTeams()
    }

    fun initializeSocket() {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    suspend fun getUserById(userId: String): UserResponse {
        return webService.getUserById(userId)
    }

    suspend fun updateUserById(userId: String, user: UserResponse) {
        return webService.updateUserById(userId, user)
    }
}
package com.project.model

import android.util.Log
import com.project.model.api.TeamsWebService
import com.project.model.response.MessagesResponse
import com.project.model.response.NewTeam
import com.project.model.response.TeamResponse
import com.project.model.response.UserResponse

class TeamRepository(private val webService: TeamsWebService = TeamsWebService()) {

    suspend fun getTeamById(id: String): TeamResponse {
        return webService.getTeamById(id)
    }

    suspend fun getMessagesById(teamId: String): MessagesResponse {
        return webService.getMessagesById(teamId)
    }
    suspend fun getUserById(userId: String): UserResponse {
        return webService.getUserById(userId)
    }
    suspend fun updateUserById(userId: String, user: UserResponse) {
        return webService.updateUserById(userId, user)
    }

    suspend fun createTeam(newTeam: NewTeam) {
        return webService.createTeam(newTeam)
    }


}
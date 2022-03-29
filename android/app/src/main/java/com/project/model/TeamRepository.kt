package com.project.model

import android.util.Log
import com.project.model.api.TeamsWebService
import com.project.model.response.MessagesResponse
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.conversation.Message

class TeamRepository(private val webService: TeamsWebService = TeamsWebService()) {

    suspend fun getTeamById(id: String): TeamResponse {
        return webService.getTeamById(id)
    }

    suspend fun getMessagesById(teamId: String): MessagesResponse {
        return webService.getMessagesById(teamId)
    }

}
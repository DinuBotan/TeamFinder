package com.project.model

import com.project.model.api.TeamsWebService
import com.project.model.response.MessagesResponse
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.conversation.Message

class TeamRepository(private val webService: TeamsWebService = TeamsWebService()) {

    suspend fun getTeamById(id: String): TeamResponse {
        return webService.getTeamById(id)
    }

    suspend fun postMessage(msg: Message) {
        webService.postMessage(msg)
    }

    suspend fun getMessages(): MessagesResponse {
        return webService.getMessages()
    }

}
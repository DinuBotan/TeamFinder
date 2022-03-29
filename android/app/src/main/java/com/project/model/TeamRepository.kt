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

    suspend fun postMessage(msg: Message) {
        webService.postMessage(msg)
    }

    suspend fun getMessages(): MessagesResponse {
        return webService.getMessages()
    }

    suspend fun getMessagesById(teamId: String): MessagesResponse {
        Log.d("Messages team id in team repository: ", teamId)
        Log.d("Messages in team repo: ", webService.getMessagesById(teamId).toString())
        return webService.getMessagesById(teamId)
    }

}
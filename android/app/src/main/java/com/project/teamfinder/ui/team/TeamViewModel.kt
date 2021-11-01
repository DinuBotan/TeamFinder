package com.project.teamfinder.ui.team

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamRepository
import com.project.model.api.SocketHandler
import com.project.model.response.MessageResponse
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.conversation.ConversationUiState
import com.project.teamfinder.ui.conversation.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: TeamRepository = TeamRepository()) : ViewModel() {

    private val teamState: MutableStateFlow<TeamResponse> = MutableStateFlow(TeamResponse("", "", 0))

    fun getTeamById(id: String): TeamResponse {
        viewModelScope.launch(Dispatchers.IO) {
            teamState.value = getTeam(id)
            Log.d("teamStateGetById", teamState.value.name)
        }
        return teamState.value
    }

    // Suspend functions are asynchronous and should wait for the result inside a coroutine
    private suspend fun getTeam(id: String): TeamResponse {
        return repository.getTeamById(id)
    }

    init {
        SocketHandler.setSocket()

        val mSocket = SocketHandler.getSocket()

        mSocket.connect()

        mSocket.emit("message")
    }

    val teamMessages: MutableState<List<MessageResponse>> = mutableStateOf(emptyList<MessageResponse>())
    var teamMessages2 : ArrayList<Message> = ArrayList()
    val teamUiState = ConversationUiState(
        teamMessages2
    )
//
    fun addMessage(m : Message) {
        Log.d("teamViewModel", "Added message: " + m.content)
        Log.d("teamViewModel", "Messages in list: $teamMessages")

        viewModelScope.launch(Dispatchers.IO) {
            repository.postMessage(m)
        }
    }

}

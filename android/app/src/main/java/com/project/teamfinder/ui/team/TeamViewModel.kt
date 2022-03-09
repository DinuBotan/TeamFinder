package com.project.teamfinder.ui.team

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project.model.TeamRepository
import com.project.model.api.SocketHandler
import com.project.model.response.MessageResponse
import com.project.model.response.MessagesResponse
import com.project.model.response.TeamResponse
import com.project.teamfinder.data.exampleUiState
import com.project.teamfinder.data.initialMessages
import com.project.teamfinder.ui.conversation.ConversationUiState
import com.project.teamfinder.ui.conversation.Message
import io.socket.client.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class User (
        var username : String,
        var room : String
)

class TeamViewModel(private val repository: TeamRepository = TeamRepository()) : ViewModel() {
//    val teamMessages: MutableState<MessagesResponse> = mutableStateOf(MessagesResponse(emptyList()))
    lateinit var messages: MessagesResponse

    init {
        viewModelScope.launch(Dispatchers.IO) {
             messages = getMessages()
//            teamMessages.value = messages
        }
    }

    private val teamState: MutableStateFlow<TeamResponse> = MutableStateFlow(TeamResponse("", "", 0))
    private var socket: Socket = SocketHandler.getSocket()
    private var gson: Gson = Gson()
    private lateinit var team: TeamResponse


    fun getTeamById(id: String): TeamResponse {
        viewModelScope.launch(Dispatchers.IO) {
            teamState.value = getTeam(id)
            Log.d("teamStateGetById", teamState.value.name)
        }
        return teamState.value
    }

    private suspend fun getMessages(): MessagesResponse {
        settMessages(repository.getMessages())
        return repository.getMessages()
    }

    // Suspend functions are asynchronous and should wait for the result inside a coroutine
    private suspend fun getTeam(id: String): TeamResponse {
        return repository.getTeamById(id)
    }
    var teamMessages2 : ArrayList<Message> = ArrayList()

    private fun settMessages(messages: MessagesResponse) {
        for(message in messages.messages) {
            var msg = Message(message.author, message.content, message.timestamp, "")
            teamMessages2.add(0, msg)
            setTeamUiState()
        }
    }

    lateinit var teamUiState: ConversationUiState

    private fun setTeamUiState() {
        teamUiState = ConversationUiState(
            teamMessages2

//    teamMessages
        )
    }

//    var teamMessages2 : ArrayList<Message> = ArrayList()



    fun joinChat(id: String) {
        team = getTeamById(id)
        val user = gson.toJson(User("user1", team.name))
        socket.emit("join", user)
    }

    fun addMessage(m : Message) {
        Log.d("teamViewModel", "Added message: " + m.content)
//        Log.d("teamViewModel", "Messages in list: $teamMessages")

        m.chatRoomID = team.name
        socket.emit("sendMessage", Gson().toJson(m))

//        viewModelScope.launch(Dispatchers.IO) {
//            repository.postMessage(m)
//        }
    }

}

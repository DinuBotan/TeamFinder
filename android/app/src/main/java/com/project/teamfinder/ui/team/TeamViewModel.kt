package com.project.teamfinder.ui.team

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project.model.TeamRepository
import com.project.model.api.SocketHandler
import com.project.model.response.MessageResponse
import com.project.model.response.MessagesResponse
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.conversation.ConversationUiState
import com.project.teamfinder.ui.conversation.Message
import io.socket.client.Socket
import io.socket.emitter.Emitter
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
    private var fetched = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            setTeamUiState()
        }
    }

    private val teamState: MutableStateFlow<TeamResponse> = MutableStateFlow(TeamResponse("", "", 0))
    private var socket: Socket = SocketHandler.getSocket()
    private var gson: Gson = Gson()
    lateinit var teamId: String

    fun getTeamById(id: String): TeamResponse {
        viewModelScope.launch(Dispatchers.IO) {
            teamState.value = getTeam(id)
            getMessagesById(id)
        }
        return teamState.value
    }

    private suspend fun getMessagesById(teamId: String) {
        messages = repository.getMessagesById(teamId)
        setMessages(messages)
    }

    // Suspend functions are asynchronous and should wait for the result inside a coroutine
    private suspend fun getTeam(id: String): TeamResponse {
        return repository.getTeamById(id)
    }
    var teamMessages2 : ArrayList<Message> = ArrayList()

    @JvmName("setMessages1")
    private fun setMessages(messages: MessagesResponse) {
        for(message in messages.messages) {
            var msg = Message(message.author, message.content, message.timestamp, teamId)
            teamMessages2.add(0, msg)
        }
        if(!fetched) {
            fetched = true
            updateTeamUiState(teamMessages2)
        }
    }

    lateinit var teamUiState: ConversationUiState

    private fun setTeamUiState() {
        teamUiState = ConversationUiState(
            teamMessages2
        )
    }

    private fun updateTeamUiState(messages: ArrayList<Message>) {
        Log.d("updateChat inside updateTeamUi ", messages.toString())
            teamUiState.messages.addAll(0, messages)

    }

    fun joinChat(id: String) {
        val user = gson.toJson(User("user1", teamState.value.name))
        socket.emit("join", user)
        socket.on("chatMessage", updateChat)
    }

    var updateChat = Emitter.Listener {
        Log.d("updateChat ", it[0].toString())
        val chat:MessageResponse = gson.fromJson(it[0].toString(), MessageResponse::class.java)
        Log.d("Chat ", chat.toString())
        var message = Message(chat.author, chat.content, chat.timestamp, teamId)
        var messages: ArrayList<Message> = ArrayList()
        messages.add(message)
        Log.d("updateChat messages: ", messages.toString())
        updateTeamUiState(messages)
    }

    fun addMessage(m : Message) {
        m.chatRoomID = teamId
        socket.emit("sendMessage", Gson().toJson(m))
    }

}

package com.project.teamfinder.ui.team

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project.model.TeamRepository
import com.project.model.api.SocketHandler
import com.project.model.response.MessageResponse
import com.project.model.response.MessagesResponse
import com.project.model.response.TeamResponse
import com.project.model.response.UserResponse
import com.project.teamfinder.ui.conversation.ConversationUiState
import com.project.teamfinder.ui.conversation.Message
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class User (
        var username : String,
        var room : String
)

class TeamViewModel(private val repository: TeamRepository = TeamRepository()) : ViewModel() {
    lateinit var messages: MessagesResponse
    var teamMessages : ArrayList<Message> = ArrayList()
    val _team: MutableLiveData<TeamResponse> = MutableLiveData(TeamResponse("", "", 0,1))
    val team: LiveData<TeamResponse> = _team
    lateinit var user: UserResponse

    init {
        viewModelScope.launch(Dispatchers.IO) {
            setTeamUiState()
            user = repository.getUserById(userId)
        }
    }

    private var socket: Socket = SocketHandler.getSocket()
    private var gson: Gson = Gson()
    lateinit var teamId: String
    lateinit var userId: String
    val openDialog = mutableStateOf(false)

    fun getTeamById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _team.postValue(getTeam(id))
            getMessagesById(id)
        }
    }

    private suspend fun getMessagesById(teamId: String) {
        messages = repository.getMessagesById(teamId)
        Thread.sleep(500)
        setMessages(messages)
    }

    // Suspend functions are asynchronous and should wait for the result inside a coroutine
    private suspend fun getTeam(id: String): TeamResponse {
        return repository.getTeamById(id)
    }

    @JvmName("setMessages1")
    private fun setMessages(messages: MessagesResponse) {
        for(message in messages.messages) {
            var msg = Message(message.authorId, message.content, message.timestamp, teamId)
            teamMessages.add(0, msg)
        }
            updateTeamUiState(teamMessages)
    }

    lateinit var teamUiState: ConversationUiState

    private fun setTeamUiState() {
        teamUiState = ConversationUiState(
            teamMessages
        )
    }

    private fun updateTeamUiState(messages: ArrayList<Message>) {
        teamUiState.messages.addAll(0, messages)
    }

    fun joinChat(id: String) {
        val user = gson.toJson(User("user1", id))
        socket.emit("join", user)
        socket.on("chatMessage", updateChat)
    }

    var updateChat = Emitter.Listener {
        val chat:MessageResponse = gson.fromJson(it[0].toString(), MessageResponse::class.java)
        var message = Message(chat.authorId, chat.content, chat.timestamp, teamId)
        var messages: ArrayList<Message> = ArrayList()
        messages.add(message)
        updateTeamUiState(messages)
    }

    fun leaveChat(id: String) {
        val user = gson.toJson(User("user1", id))
        socket.emit("leaveChat", user)
    }

    fun addMessage(m : Message) {
        m.chatRoomId = teamId
        socket.emit("sendMessage", Gson().toJson(m))
    }

    fun leaveTeam(teamId: String) {
        user.teams.remove(teamId)
        updateUserById(userId, user)
    }

    private fun updateUserById(userId: String, user: UserResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserById(userId, user)
        }
    }
}

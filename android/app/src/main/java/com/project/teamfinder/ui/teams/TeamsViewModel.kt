package com.project.teamfinder.ui.teams

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamsRepository
import com.project.model.response.TeamResponse
import com.project.model.response.User
import com.project.model.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// our ViewModel will be of type Android ViewModel, thus android will take care of its lifecycle...
class TeamsViewModel(private val repository: TeamsRepository = TeamsRepository()): ViewModel() {
    lateinit var userId: String
    lateinit var user: UserResponse

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val teams = getTeams()
            user = repository.getUserById(userId)
            Thread.sleep(500)
            teamsState.value = teams
            repository.initializeSocket()
        }
    }

    val teamsState: MutableState<List<TeamResponse>> = mutableStateOf(emptyList<TeamResponse>())

    // Suspend functions are asynchronous and should wait for the result inside a coroutine
    private suspend fun getTeams(): List<TeamResponse> {
        return repository.getTeams().teams
    }

    fun belongsToTeam(teamId: String): Boolean {
        Log.d("User: ", user.toString())
        Log.d("UserTeamId:", teamId)
        if(user.teams.contains(teamId)) {
            Log.d("UserTrue:", "true")
            return true
        }
        return false
    }

    fun joinTeam(teamId: String) {
        user.teams.add(teamId)
        updateUserById(userId, user)
    }

    fun updateUserById(userId: String, user: UserResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserById(userId, user)
        }
    }
}
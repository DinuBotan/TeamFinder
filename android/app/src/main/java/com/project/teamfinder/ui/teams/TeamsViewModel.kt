package com.project.teamfinder.ui.teams

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamsRepository
import com.project.model.response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// our ViewModel will be of type Android ViewModel, thus android will take care of its lifecycle...
class TeamsViewModel(private val repository: TeamsRepository = TeamsRepository()): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val teams = getTeams()
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
}
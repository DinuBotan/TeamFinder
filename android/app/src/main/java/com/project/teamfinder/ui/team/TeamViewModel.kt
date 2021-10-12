package com.project.teamfinder.ui.team

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamsRepository
import com.project.model.response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: TeamsRepository = TeamsRepository()) : ViewModel() {

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

}

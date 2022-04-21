package com.project.teamfinder.ui.createTeam


import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamRepository
import com.project.model.response.NewTeam
import com.project.model.response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTeamViewModel(private val repository: TeamRepository = TeamRepository()): ViewModel() {
    var name = mutableStateOf("")
    var category = mutableStateOf("")
    var size = mutableStateOf("")
    var country = mutableStateOf("")
    var city = mutableStateOf("")
    var language = mutableStateOf("")
    val pickedImage = mutableStateOf<Uri?>(null)

    fun createTeam() {
        val newTeam = NewTeam(name.value, Integer.parseInt(size.value))
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTeam(newTeam)
        }
    }
}




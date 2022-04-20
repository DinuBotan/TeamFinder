package com.project.teamfinder.ui.createTeam


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NewTeamViewModel(): ViewModel() {
    var name = mutableStateOf("")
    var category = mutableStateOf("")
    var size = mutableStateOf("")
    var country = mutableStateOf("")
    var city = mutableStateOf("")
    var language = mutableStateOf("")
}
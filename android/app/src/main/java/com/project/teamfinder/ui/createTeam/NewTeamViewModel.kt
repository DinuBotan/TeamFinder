package com.project.teamfinder.ui.createTeam


import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamRepository
import com.project.model.response.NewTeam
import com.project.model.response.TeamResponse
import com.project.teamfinder.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTeamViewModel(private val repository: TeamRepository = TeamRepository()): ViewModel() {
    var name = mutableStateOf("")
    var category = mutableStateOf("")
    var size = mutableStateOf("")
    var country = mutableStateOf("")
    var city = mutableStateOf("")
    var language = mutableStateOf("")
    val imageId = mutableStateOf<Int>(getRandomNumber())
//    val imageMap: HashMap<Int, Int> = populateMap()
    val teamImage: Int = getImage(imageId.value)

    private fun getRandomNumber(): Int {
        return (1..2).random()
    }

    private fun getImage(teamNum: Int): Int {
        var imageMap: HashMap<Int, Int> = HashMap()
        imageMap.put(1, R.drawable.team1)
        imageMap.put(2, R.drawable.team2)
        if(imageMap.get(teamNum) != null) {
            return imageMap.get(teamNum)!!
        }
        return R.drawable.team1

    }

    fun createTeam() {
        val newTeam = NewTeam(name.value, Integer.parseInt(size.value), imageId.value)
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTeam(newTeam)
        }
    }
}




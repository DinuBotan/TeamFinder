package com.project.teamfinder.ui.createTeam


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.TeamRepository
import com.project.model.response.NewTeam
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
    val location = mutableStateOf("")
//    val imageMap: HashMap<Int, Int> = populateMap()
    val teamImage: Int = getImage(imageId.value)

    private fun getRandomNumber(): Int {
        return (1..6).random()
    }

    private fun getImage(teamNum: Int): Int {
        var imageMap: HashMap<Int, Int> = HashMap()
        imageMap.put(1, R.drawable.team1)
        imageMap.put(2, R.drawable.team2)
        imageMap.put(3, R.drawable.team3)
        imageMap.put(4, R.drawable.team4)
        imageMap.put(5, R.drawable.team5)
        imageMap.put(6, R.drawable.team6)
        if(imageMap.get(teamNum) != null) {
            return imageMap.get(teamNum)!!
        }
        return R.drawable.team1

    }

    fun createTeam() {
        val newTeam = NewTeam(
            name.value,
            Integer.parseInt(size.value),
            imageId.value,
            category.value,
            location.value,
            language.value
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTeam(newTeam)
        }
    }
}




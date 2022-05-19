package com.project.teamfinder.ui.signUp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.UserRepository
import com.project.model.response.UserResponse
import com.project.teamfinder.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {

    val interests: ArrayList<String> = ArrayList()
    val teams: ArrayList<String> = ArrayList()
    var user: UserResponse = UserResponse("0", "No name", "", "No password", "", "", "", interests, teams, 1)
    val emailTextBoxState: MutableState<String> = mutableStateOf("")
    val passwordTextBoxState: MutableState<String> = mutableStateOf("")
    val nameTextBoxState: MutableState<String> = mutableStateOf("")
    val userCreated: MutableState<Boolean> = mutableStateOf(false)
    val imageId = mutableStateOf<Int>(getRandomNumber())
    val userImage: Int = getImage(imageId.value)

    private fun getRandomNumber(): Int {
        return (1..3).random()
    }

    fun createAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createUser(nameTextBoxState.value, emailTextBoxState.value, passwordTextBoxState.value)
            userCreated.value = true
        }
    }

    private fun getImage(teamNum: Int): Int {
        var imageMap: HashMap<Int, Int> = HashMap()
        imageMap.put(1, R.drawable.user1)
        imageMap.put(2, R.drawable.user2)
        imageMap.put(3, R.drawable.user3)
        if(imageMap.get(teamNum) != null) {
            return imageMap.get(teamNum)!!
        }
        return R.drawable.user1

    }

}
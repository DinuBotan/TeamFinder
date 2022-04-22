package com.project.teamfinder.ui.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.model.UserRepository
import com.project.model.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {

    val interests: ArrayList<String> = ArrayList()
    val teams: ArrayList<String> = ArrayList()
    var user: UserResponse = UserResponse("0", "No name", "", "No password", "", "", "", interests, teams, 1)
    val emailTextBoxState: MutableState<String> = mutableStateOf("")
    val passwordTextBoxState: MutableState<String> = mutableStateOf("")
    val successfulLogin: MutableState<Boolean> = mutableStateOf(false)
    val unsuccessfulLogin: MutableState<Boolean> = mutableStateOf(false)

    fun login(): UserResponse {
        viewModelScope.launch(Dispatchers.IO) {
            user = repository.login(emailTextBoxState.value, passwordTextBoxState.value)
            if(user.email.length > 2) {
                successfulLogin.value = true
            } else {
                unsuccessfulLogin.value = true
            }
        }
        return user
    }
}
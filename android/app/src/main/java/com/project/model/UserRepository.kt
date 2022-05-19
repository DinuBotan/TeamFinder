package com.project.model

import com.project.model.api.TeamsWebService
import com.project.model.response.NewUser
import com.project.model.response.User
import com.project.model.response.UserResponse

class UserRepository(private val webService: TeamsWebService = TeamsWebService()) {

    suspend fun login(userEmail: String, userPassword: String): UserResponse {
        val interests: ArrayList<String> = ArrayList()
        val teams: ArrayList<String> = ArrayList()
        val user = User(userEmail, userPassword, "", "", "", "", interests, teams, 1)
        return webService.login(user)
    }

    suspend fun createUser(userName: String, userEmail: String, userPassword: String) {
        val newUser = NewUser(userName, userEmail, userPassword)
        webService.createUser(newUser)
    }
 }
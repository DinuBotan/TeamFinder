package com.project.model

import com.project.model.api.TeamsWebService
import com.project.model.response.User
import com.project.model.response.UserResponse

class UserRepository(private val webService: TeamsWebService = TeamsWebService()) {

    suspend fun login(userEmail: String, userPassword: String): UserResponse {
        val user: User = User(userEmail, userPassword)
        return webService.login(user)
    }
 }
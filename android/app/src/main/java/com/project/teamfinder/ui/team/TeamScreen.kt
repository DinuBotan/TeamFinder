package com.project.teamfinder.ui.team

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.model.response.TeamResponse


@Composable
fun TeamScreen(team: TeamResponse = TeamResponse("1", "Team 1", "10")) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            TeamCard(team = team)
        }
    }
}

@Composable
fun AppBar() {

}

@Composable
fun TeamCard(team: TeamResponse) {
    var team : TeamResponse;
}
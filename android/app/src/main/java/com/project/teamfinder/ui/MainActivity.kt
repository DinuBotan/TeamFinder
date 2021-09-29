package com.project.teamfinder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.project.teamfinder.ui.teams.TeamsScreen
import com.project.teamfinder.ui.theme.TeamFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamFinderTheme {
                TeamsScreen()
            }
        }
    }
}

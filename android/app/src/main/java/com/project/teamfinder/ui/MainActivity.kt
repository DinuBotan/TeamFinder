package com.project.teamfinder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.teamfinder.ui.team.TeamScreen
import com.project.teamfinder.ui.teams.TeamsScreen
import com.project.teamfinder.ui.theme.TeamFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamFinderTheme {
                TeamsApplication()
            }
        }
    }
}

@Composable
fun TeamsApplication() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "teams_list") {
        composable("teams_list") {
            TeamsScreen(navController)
        }
        composable(
            route = "team_details/{teamId}",
            arguments = listOf(navArgument("teamId") {
            type = NavType.StringType
        })
        ) {
            navBackStackEntry ->
            TeamScreen(navBackStackEntry.arguments!!.getString("teamId").toString(), navController)
        }
    }
}

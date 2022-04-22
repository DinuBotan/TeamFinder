package com.project.teamfinder.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.teamfinder.ui.createTeam.NewTeamScreen
import com.project.teamfinder.ui.team.TeamScreen
import com.project.teamfinder.ui.teams.TeamsScreen
import com.project.teamfinder.ui.login.loginScreen
import com.project.teamfinder.ui.search.SearchScreen
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
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            loginScreen(navController)
        }
        composable(
            route = "teams_list/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ) {
            navBackStackEntry ->
            TeamsScreen(navBackStackEntry.arguments!!.getString("userId").toString(), navController)
        }
        composable(
            route = "new_team/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ) {
                navBackStackEntry ->
            NewTeamScreen(navBackStackEntry.arguments!!.getString("userId").toString(), navController)
        }
        composable(
            route = "search/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ) {
                navBackStackEntry ->
            SearchScreen(navBackStackEntry.arguments!!.getString("userId").toString(), navController)
        }
        composable(
            route = "team_details/{teamId}&{userId}",
            arguments = listOf(
                navArgument("teamId") {
                type = NavType.StringType
        },
            navArgument("userId") {
                type = NavType.StringType
            }
            )
        ) {
            navBackStackEntry ->
            TeamScreen(navBackStackEntry.arguments!!.getString("teamId").toString(), navBackStackEntry.arguments!!.getString("userId").toString(), navController)
            Log.d("TeamScreen", "called")
        }
    }
}

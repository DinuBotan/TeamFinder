package com.project.teamfinder.ui.teams

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.project.model.response.ImagePicker
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.TeamsApplication
import com.project.teamfinder.ui.conversation.ConversationContent
import com.project.teamfinder.ui.team.TeamContent
import com.project.teamfinder.ui.team.TeamViewModel
import com.project.teamfinder.ui.theme.TeamFinderTheme

@Composable
fun TeamsScreen(userId: String, navController: NavHostController) {
    // Special syntax to instantiate a viewModel
    val viewModel: TeamsViewModel = viewModel()
    viewModel.userId = userId
    val teams = viewModel.teamsState.value
    val imagePicker = ImagePicker()

    Log.d("DebugTeams ", teams.toString())

    Scaffold(topBar = {
        AppBar(
            title = "Teams list",
            navController,
            userId
        )
    }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {}
        }
    }

    LazyColumn(
//        contentPadding = PaddingValues(60.dp),
        modifier = Modifier.absolutePadding(10.dp, 50.dp, 10.dp, 3.dp)
    ) {
        items(teams) { team ->
            if (team.members.contains(userId)) {
                Team(team, viewModel, navController, userId, imagePicker) {
                    if (viewModel.belongsToTeam(team.id)) {
                        navController?.navigate("team_details/${team.id}&${userId}")
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(title: String, navController: NavHostController, userId: String) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Search team",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = {
                        navController?.navigate("search/${userId}")
                    })
            )
            Icon(
                Icons.Outlined.Create,
                contentDescription = "Create team",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = {
                        navController?.navigate("new_team/${userId}")
                    })
            )
        }
    )
}

@Composable
fun Team(team : TeamResponse, viewModel: TeamsViewModel, navController: NavHostController, userId: String, imagePicker: ImagePicker, clickAction: () -> Unit) {
    Card(shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = { clickAction.invoke() })
    ) {
        Row {
             Image
            Image(
                painter = rememberImagePainter(imagePicker.getImage(team.imageId)),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
            )

            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp)
            ) {
                Text(text = team.name,
                    style = MaterialTheme.typography.h6
                )
                Text(text = "Members: " + team.members.size.toString() + "/" + team.size.toString(),
                    style = MaterialTheme.typography.h6
                )
                Text(text = "Category: " + team.category,
                    style = MaterialTheme.typography.h6)

                Text(text = "Location: " + team.location,
                    style = MaterialTheme.typography.h6)

                Text(text = "Language: " + team.language,
                    style = MaterialTheme.typography.h6)
            }
        }
        if(!viewModel.belongsToTeam(team.id)) {
            JoinButton(viewModel, team, navController, userId)
        }

    }
}


@Composable
fun JoinButton(viewModel: TeamsViewModel, team: TeamResponse, navController: NavHostController, userId: String) {
    Column(modifier = Modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedButton(onClick = {
            viewModel.joinTeam(team.id)
            navController?.navigate("team_details/${team.id}&${userId}")
        }) {
            Text("Join")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TeamFinderTheme {
        TeamsApplication()
    }
}

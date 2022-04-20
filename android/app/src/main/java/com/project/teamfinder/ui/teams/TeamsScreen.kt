package com.project.teamfinder.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.TeamsApplication
import com.project.teamfinder.ui.theme.TeamFinderTheme

@Composable
fun TeamsScreen(userId: String, navController: NavHostController) {
    // Special syntax to instantiate a viewModel
    val viewModel: TeamsViewModel = viewModel()
    viewModel.userId = userId
    val teams = viewModel.teamsState.value

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(teams) { team ->
            Team(team, viewModel, navController, userId) {
                if(viewModel.belongsToTeam(team.id)) {
                    navController?.navigate("team_details/${team.id}&${userId}")
                }
            }
        }
    }
}

@Composable
fun Team(team : TeamResponse, viewModel: TeamsViewModel, navController: NavHostController, userId: String, clickAction: () -> Unit) {
    Card(shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = { clickAction.invoke() })
    ) {
        Row {
            // Image
//            Image(
//                painter = rememberImagePainter(team.imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(88.dp)
//                    .padding(4.dp)
//            )

            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp)
            ) {
                Text(text = team.name,
                    style = MaterialTheme.typography.h6
                )
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

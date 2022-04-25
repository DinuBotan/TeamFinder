package com.project.teamfinder.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.project.model.response.TeamResponse
import com.project.teamfinder.ui.createTeam.AppTextField
import com.project.teamfinder.ui.teams.JoinButton
import com.project.teamfinder.ui.teams.TeamsViewModel


@Composable
fun SearchScreen(userId: String, navController: NavHostController) {
    val viewModel: SearchViewModel = viewModel()
    viewModel.userId = userId
    val teams = viewModel.teamsState.value
    val focusManager = LocalFocusManager.current
    AppTextField(
        modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 10.dp),
        text = viewModel.teamName.value,
        placeholder = "Search team",
        onChange = {
            viewModel.searchTeamByName(it)
        },
        imeAction = ImeAction.Next,//Show next as IME button
        keyboardType = KeyboardType.Text, //Plain text keyboard
        keyBoardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    LazyColumn(
//        contentPadding = PaddingValues(60.dp),
        modifier = Modifier.absolutePadding(10.dp, 50.dp, 10.dp, 3.dp)
    ) {
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
fun Team(team : TeamResponse, viewModel: SearchViewModel, navController: NavHostController, userId: String, clickAction: () -> Unit) {
    Card(shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = { clickAction.invoke() })
    ) {
        Row {
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
fun JoinButton(viewModel: SearchViewModel, team: TeamResponse, navController: NavHostController, userId: String) {
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


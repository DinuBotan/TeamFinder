package com.project.teamfinder.ui.team

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.project.model.response.TeamResponse
import com.project.teamfinder.R
import com.project.teamfinder.ui.conversation.ConversationContent



@Composable
fun TeamScreen(teamId: String, userId: String, navController: NavHostController, viewModel: TeamViewModel = viewModel()) {
    viewModel.teamId = teamId
    Log.d("DebugTeamId ", teamId)
    viewModel.userId = userId
    val team: TeamResponse by viewModel.team.observeAsState(TeamResponse("",
        "",
        0,
        1,
        ArrayList(),
        "",
        "",
        "",
        "",
        "",
        ""
    ))
    if(team.id == "") {
        viewModel.getTeamById(teamId)
        viewModel.joinChat(teamId)
    }



    Scaffold(topBar = {
        AppBar(
            title = "Team details",
            icon = Icons.Default.ArrowBack,
            viewModel,
            teamId,
            navController,
            userId
        ) {
            viewModel.leaveChat(teamId)
            navController?.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalAlignment = Arrangement.Top
            ) {
                TeamContent(TeamName = team.name, TeamSize = team.size, teamMembers = team.members.size, alignment = Alignment.CenterHorizontally)
                // use the material divider
                Divider(color = androidx.compose.ui.graphics.Color.Black, thickness = 1.dp)
                ConversationContent(viewModel.teamUiState, viewModel, userId)
            }
        }
    }
}

@Composable
fun AppBar(title: String, icon: ImageVector, viewModel: TeamViewModel, teamId: String, navController: NavHostController, userId: String, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        },
        title = { Text(title) },
        actions = {
            Icon(
                Icons.Outlined.ExitToApp,
                contentDescription = "Leave",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = {
                        viewModel.openDialog.value = true
                    })
            )
        }
    )
    if (viewModel.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                viewModel.openDialog.value = false
            },
            title = {
                Text(text = "Leave Team")
            },
            text = {
                Text("Are you sure you want to leave this team?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.openDialog.value = false
                        viewModel.leaveTeam(teamId)
                        navController?.navigate("teams_list/${userId}")
                    }) {
                    Text("Leave")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.openDialog.value = false
                    }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun TeamContent(TeamName: String, TeamSize: Int, teamMembers: Int, alignment: Alignment.Horizontal) {
    Row() {
        Column(
            modifier = Modifier
                .padding(2.dp, 4.dp, 0.dp, 4.dp),
            horizontalAlignment = alignment
        ) {
            CompositionLocalProvider(
                LocalContentAlpha provides (
                        ContentAlpha.medium)
            ) {
                Text(
                    text = TeamName,
                    style = MaterialTheme.typography.h5
                )
            }
        }
        Column(
            modifier = Modifier.padding(40.dp, 8.dp, 0.dp, 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Size",
                    modifier = Modifier.size(25.dp).padding(0.dp, 0.dp, 3.dp, 0.dp),
                    tint = androidx.compose.ui.graphics.Color.Unspecified,
                )
                Text(text = "$teamMembers/$TeamSize",
                    style = MaterialTheme.typography.h6,
                )
            }

        }
    }

}

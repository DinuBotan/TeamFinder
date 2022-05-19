package com.project.teamfinder.ui.search

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.project.model.response.ImagePicker
import com.project.model.response.TeamResponse
import com.project.teamfinder.R
import com.project.teamfinder.ui.createTeam.AppTextField
import com.project.teamfinder.ui.teams.JoinButton
import com.project.teamfinder.ui.teams.TeamsViewModel


@Composable
fun SearchScreen(userId: String, navController: NavHostController) {
    val viewModel: SearchViewModel = viewModel()
    viewModel.userId = userId
    val teams = viewModel.teamsState.value
    val focusManager = LocalFocusManager.current
    val imagePicker = ImagePicker()

    ScrollableTabRow(
        selectedTabIndex = 0,
        Modifier.fillMaxWidth(),
    ) {
        for(category in getAllTeamCategories()) {
//            Text(
//                text = category.value,
//                style = MaterialTheme.typography.body2,
//                color = MaterialTheme.colors.secondary,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .clickable {
//                        viewModel.searchTeamByCategory(category.value)
//                    }
//            )
            categoryButton(viewModel = viewModel, category = category.value)
        }


    }
    AppTextField(
        modifier = Modifier.padding(20.dp, 50.dp, 20.dp, 15.dp),
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
        modifier = Modifier.absolutePadding(10.dp, 100.dp, 10.dp, 3.dp)
    ) {
        items(teams) { team ->
            Team(team, viewModel, navController, imagePicker, userId) {
                if(viewModel.belongsToTeam(team.id)) {
                    navController?.navigate("team_details/${team.id}&${userId}")
                }
            }
        }
    }
}

@Composable
fun Team(team : TeamResponse, viewModel: SearchViewModel, navController: NavHostController, imagePicker: ImagePicker,
         userId: String, clickAction: () -> Unit) {
    Card(shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = { clickAction.invoke() })
    ) {
        Row {
            Role.Image
            Image(
                painter = rememberImagePainter(imagePicker.getImage(team.imageId)),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp)
            )

            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp)
            ) {
                Row() {
                    Text(text = team.name,
                        style = MaterialTheme.typography.h6
                    )
                }
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_category),
                        contentDescription = "Category",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Text(text = team.category,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Text(text = team.location,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_language),
                        contentDescription = "Language",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Text(text = team.language,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp)
                    )
                }

            }
        }
        if(!viewModel.belongsToTeam(team.id)) {
            JoinButton(viewModel, team, navController, userId)
        }
        Row(
            modifier = Modifier.padding(10.dp, 110.dp, 0.dp, 10.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Size",
                modifier = Modifier.size(20.dp).padding(2.dp, 5.dp, 2.dp, 0.dp),
                tint = Color.Unspecified
            )
            Text(text = team.members.size.toString() + "/" + team.size.toString(),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Composable
fun JoinButton(viewModel: SearchViewModel, team: TeamResponse, navController: NavHostController, userId: String) {
    Column(modifier = Modifier.padding(0.dp, 110.dp, 10.dp, 10.dp),
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

@Composable
fun categoryButton(viewModel: SearchViewModel, category: String) {
        OutlinedButton(onClick = {
            viewModel.searchTeamByCategory(category)
        }, modifier = Modifier
            .padding(4.dp)
        ) {
            Text(category)
        }
}

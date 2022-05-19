package com.project.teamfinder.ui.createTeam

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.project.teamfinder.R

@Composable
fun NewTeamScreen(userId: String, navController: NavHostController, viewModel: NewTeamViewModel = viewModel()) {
//    AppTextField(placeholder = "Enter text here", text = "")
    ProvideWindowInsets {
        Column{
            ImagePickerView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 30.dp),
                viewModel.teamImage,
//                lastSelectedImage = viewModel.pickedImage.value,
                onSelection = {
//                    viewModel.pickedImage.value = it
                }
            )
            val focusManager = LocalFocusManager.current
            AppTextField(
                modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp),
                text = viewModel.name.value,
                placeholder = "Team Name",
                onChange = {
                    viewModel.name.value = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.team),
                        contentDescription = "Name",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
            AppTextField(
                modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 10.dp),
                text = viewModel.category.value,
                placeholder = "Category",
                onChange = {
                    viewModel.category.value = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_category),
                        contentDescription = "Category",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
            AppTextField(
                modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 10.dp),
                text = viewModel.size.value,
                placeholder = "Size",
                onChange = {
                    viewModel.size.value = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Size",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
            AppTextField(
                modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 10.dp),
                text = viewModel.location.value,
                placeholder = "Location",
                onChange = {
                    viewModel.location.value = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
//            AppTextField(
//                modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 10.dp),
//                text = viewModel.city.value,
//                placeholder = "City",
//                onChange = {
//                    viewModel.city.value = it
//                },
//                imeAction = ImeAction.Next,//Show next as IME button
//                keyboardType = KeyboardType.Text, //Plain text keyboard
//                keyBoardActions = KeyboardActions(
//                    onNext = {
//                        focusManager.moveFocus(FocusDirection.Down)
//                    }
//                )
//            )
            AppTextField(
                modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 10.dp),
                text = viewModel.language.value,
                placeholder = "Language",
                onChange = {
                    viewModel.language.value = it
                },
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_language),
                        contentDescription = "Language",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
            Column(
                modifier = Modifier.align(Alignment.End).padding(0.dp, 15.dp, 20.dp, 0.dp)
            ) {
                CreateButton(viewModel, navController, userId)
            }
        }
    }

}

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onChange,
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        }
    )
}

@Composable
fun ImagePickerView(
    modifier: Modifier = Modifier,
//    lastSelectedImage: Uri?,
    teamImage: Int,
    onSelection: (Uri?) -> Unit
) {
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()) {
        onSelection(it)
        Log.d("ImageDebug", teamImage.toString())
    }
    Image(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable {
                galleryLauncher.launch("image/*")
            },
//        painter = rememberImagePainter(lastSelectedImage),
        painter = painterResource(teamImage),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CreateButton(viewModel: NewTeamViewModel, navController: NavHostController, userId: String) {
    OutlinedButton(onClick = {
        viewModel.createTeam()
        navController?.navigate("teams_list/${userId}")
    }) {
        Text("Create")
    }
}
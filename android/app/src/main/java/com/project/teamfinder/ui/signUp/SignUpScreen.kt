package com.project.teamfinder.ui.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.project.teamfinder.R
import com.project.teamfinder.ui.createTeam.ImagePickerView
import com.project.teamfinder.ui.login.*
import com.project.teamfinder.ui.theme.BackgroundColor
import com.project.teamfinder.ui.theme.TextFieldColor
import com.project.teamfinder.ui.theme.TextFieldTextColor

@Composable
fun signUpScreen(navController: NavHostController, viewModel: SignUpViewModel = viewModel()) {
    if(viewModel.userCreated.value) {
        navController?.navigate("login_screen")
        viewModel.userCreated.value = false
    }
    Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.BackgroundColor){
        Column (modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            WelcomeText()
            ImagePickerView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 30.dp),
                viewModel.userImage,
//                lastSelectedImage = viewModel.pickedImage.value,
                onSelection = {
//                    viewModel.pickedImage.value = it
                }
            )
            text_field(InputType = KeyboardType.Email,"E-mail address",IconImage = painterResource(id = R.drawable.ic_email), viewModel)
            CreateAccount(viewModel = viewModel)
        }
    }
}

@Composable
fun text_field(InputType : KeyboardType, placeholder : String, IconImage : Painter, viewModel: SignUpViewModel){

    TextField(value = viewModel.nameTextBoxState.value,
        onValueChange = { newInput -> viewModel.nameTextBoxState.value = newInput },
        leadingIcon = { Image(painter = painterResource(id = R.drawable.ic_user), contentDescription = "name",
            modifier = Modifier.size(40.dp)) },
        label = { Text(text = "Name",color = MaterialTheme.colors.TextFieldTextColor) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.BackgroundColor)
    )
    TextField(value = viewModel.emailTextBoxState.value,
        onValueChange = { newInput -> viewModel.emailTextBoxState.value = newInput },
        leadingIcon = { Image(painter = painterResource(id = R.drawable.ic_email), contentDescription = "email",
            modifier = Modifier.size(40.dp)) },
        label = { Text(text = "E-mail address",color = MaterialTheme.colors.TextFieldTextColor) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.BackgroundColor)
    )
    TextField(value = viewModel.passwordTextBoxState.value,
        onValueChange = { newInput -> viewModel.passwordTextBoxState.value = newInput },
        leadingIcon = { Image(painter = painterResource(id = R.drawable.ic_password), contentDescription = "password",
            modifier = Modifier.size(40.dp)) },
        label = { Text(text = "Password",color = MaterialTheme.colors.TextFieldTextColor) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.BackgroundColor)
    )
}

@Composable
fun CreateAccount(viewModel: SignUpViewModel){
    Button(onClick = {
        viewModel.createAccount()
    },modifier = Modifier
        .padding(top = 25.dp)
        .requiredWidth(277.dp)){
        Text(text = "Sign Up")
    }
}
package com.project.teamfinder.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.project.teamfinder.R
import androidx.compose.material.Text as Text
import com.project.teamfinder.ui.theme.*


@Composable
fun loginScreen(navController: NavHostController, viewModel: LoginViewModel = viewModel()) {
    if(viewModel.successfulLogin.value) {
        navController?.navigate("teams_list/${viewModel.user.id}")
        viewModel.successfulLogin.value = false
    } else if (viewModel.signUp.value) {
        navController?.navigate("sign_up_screen")
        viewModel.signUp.value = false
    }
    Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.BackgroundColor){
        Column (modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            WelcomeText()
            PurposeImage()
            text_field(InputType = KeyboardType.Email,"E-mail address",IconImage = painterResource(id = R.drawable.ic_email), viewModel)
            SignIn(viewModel)
            SignUp(viewModel)
            SnackbarDemo(viewModel)
        }
    }
}
@Composable
fun WelcomeText(){
    Text(text = "Team Finder",
        color = Color.White,
        fontSize = 25.sp,
        modifier = Modifier.padding(top = 40.dp)
    )
}
@Composable
fun PurposeImage(){
    Image(painter = painterResource(id = R.drawable.team), contentDescription = "LocationPin",
        modifier = Modifier.size(300.dp))
}
@Composable
fun text_field(InputType : KeyboardType,placeholder : String,IconImage : Painter, viewModel: LoginViewModel){

    TextField(value = viewModel.emailTextBoxState.value,
        onValueChange = { newInput -> viewModel.emailTextBoxState.value = newInput },
        leadingIcon = {Image(painter = painterResource(id = R.drawable.ic_email), contentDescription = "email",
            modifier = Modifier.size(40.dp)
        )},
        label = {Text(text = "E-mail address",color = MaterialTheme.colors.TextFieldTextColor)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.BackgroundColor)
    )
    TextField(value = viewModel.passwordTextBoxState.value,
        onValueChange = { newInput -> viewModel.passwordTextBoxState.value = newInput },
        leadingIcon = {Image(painter = painterResource(id = R.drawable.ic_password), contentDescription = "password",
            modifier = Modifier.size(40.dp))},
        label = {Text(text = "Password",color = MaterialTheme.colors.TextFieldTextColor)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.BackgroundColor)
    )
}
@Composable
fun SignIn(viewModel: LoginViewModel){
    Button(onClick = {
        viewModel.login()
    },modifier = Modifier
        .padding(top = 25.dp)
        .requiredWidth(277.dp)){
        Text(text = "Sign In")
    }
}
@Composable
fun SnackbarDemo(viewModel: LoginViewModel) {
    Column {
        if (viewModel.unsuccessfulLogin.value) {
            Log.d("Snackbar", "Showing")
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "The email or password is incorrect!") }
            viewModel.unsuccessfulLogin.value = false
        }
    }
}
@Composable
fun SignUp(viewModel: LoginViewModel){
    Button(onClick = {
        viewModel.signUp.value = true
    },modifier = Modifier
        .padding(top = 25.dp)
        .requiredWidth(277.dp)){
        Text(text = "Sign Up")
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    TeamFinderTheme(){
//        loginScreen()
//    }
//
//}
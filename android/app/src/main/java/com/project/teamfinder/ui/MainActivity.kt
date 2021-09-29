package com.project.teamfinder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.teamfinder.ui.theme.TeamFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GreetingText("Android")
                }
            }
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(text = "Hello $name!",
        modifier = Modifier.width(80.dp)
                            .height(240.dp))
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TeamFinderTheme {
        GreetingText("Android")
    }
}
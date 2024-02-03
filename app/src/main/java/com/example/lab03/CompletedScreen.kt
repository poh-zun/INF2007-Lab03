package com.example.lab03

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab03.ui.theme.Lab03Theme

@Composable
fun CompletedScreen (
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    userInput: MutableState<String>,
    totalScore: MutableState<Int>,
    totalMaxScore: MutableState<Int>
) {
    val context = LocalContext.current
    val introvertScore = (totalScore.value.toDouble()) / (totalMaxScore.value.toDouble()) * 100
    val extrovertScore = 100 - introvertScore
    val formattedIntrovertScore = String.format("%.2f", introvertScore)
    val formattedExtrovertScore = String.format("%.2f", extrovertScore)
    val outcome = "Hi ${userInput.value}, \n" +
            "You are ${formattedIntrovertScore}% introvert, and ${formattedExtrovertScore}% extrovert"

    Column (
        modifier = modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Completed!",
            style = TextStyle(fontSize = 30.sp)
        )
        Divider(
            modifier = modifier
                .padding(10.dp)
        )
        Text( text = outcome,
            modifier = modifier
                .padding(15.dp),
            style = TextStyle(fontSize = 15.sp)
        )
        Column (
            modifier = modifier
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    // Create an Intent with ACTION_SEND action
                    val intent = Intent(Intent.ACTION_SEND)
                    // Set the type of data being sent (plain text in this case)
                    intent.type = "text/plain"
                    // Set the subject of the email (optional)
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Introvert/Extrovert Test result")
                    // Set the text of the email
                    intent.putExtra(Intent.EXTRA_TEXT, outcome)

                    // Start an activity to send the email
                    context.startActivity(Intent.createChooser(intent, "Share your result"))
                }
            ) {
                Text("Share your result")
            }
            Button(
                onClick = {
                    totalScore.value = 0
                    totalMaxScore.value = 0
                    navController.navigate("question_screen")
                }
            ) {
                Text("Restart the test")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompletedScreenPreview() {
    Lab03Theme {
        val navController = rememberNavController()
        val userInput = remember { mutableStateOf("") }
        var totalScore = remember { mutableIntStateOf(0) }
        var totalMaxScore = remember { mutableIntStateOf(0) }
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CompletedScreen(Modifier, navController, userInput, totalScore, totalMaxScore)
        }
    }
}
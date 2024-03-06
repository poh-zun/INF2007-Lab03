package com.example.lab03

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun StartScreen(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    userInput: MutableState<String> = mutableStateOf("")
) {
    var isButtonEnabled by remember { mutableStateOf(false) }

    Column (modifier = modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Extrovert/Introvert Test",
            style = TextStyle(fontSize = 30.sp)
        )
        Text("Enter your name to Start",
            style = TextStyle(fontSize = 15.sp)
        )
        TextField(
            value = userInput.value,
            onValueChange = {
                userInput.value = it
                isButtonEnabled = it.isNotBlank()
            },
            label = {Text("Enter name...")},
            modifier = modifier.padding(1.dp)
        )
        Button(
            onClick = {
                navController.navigate("question_screen")
            },
            enabled = isButtonEnabled
        ){
            Text("Start")
        }
    }

}
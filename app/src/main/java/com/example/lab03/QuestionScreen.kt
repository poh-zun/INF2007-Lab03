package com.example.lab03

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab03.ui.theme.Lab03Theme

@Composable
fun QuestionScreen (
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    userInput: MutableState<String> = mutableStateOf(""),
    totalScore: MutableState<Int> = mutableIntStateOf(0),
    totalMaxScore: MutableState<Int> = mutableIntStateOf(0)
) {

    var selectedOption by remember { mutableIntStateOf(0) }
    var count = remember { mutableIntStateOf(0) }
    val threshold = 4
    var questionList = testData.questionsBank[count.intValue]

    Column (
        modifier = modifier
            .padding(16.dp),
    ) {
        Text("Question ${count.intValue + 1}",
            style = TextStyle(fontSize = 30.sp)
        )
        Text(questionList.question,
            style = TextStyle(fontSize = 15.sp)
        )
        Divider(
            modifier = modifier
                .padding(10.dp)
        )
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Radio Group
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == questionList.scores[0],
                    onClick = { selectedOption = questionList.scores[0] }
                )
                Text(questionList.options[0])
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == questionList.scores[1],
                    onClick = { selectedOption = questionList.scores[1] }
                )
                Text(questionList.options[1])
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == questionList.scores[2],
                    onClick = { selectedOption = questionList.scores[2] }
                )
                Text(questionList.options[2])
            }
            Spacer(modifier = modifier
                .height(5.dp)
            )
        }
        Column(

        ) {
            // Button
            Row (
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        navController.navigate("start_screen")
                    },
                    modifier = modifier
                        .width(150.dp)
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        for (i in 0..2) {
                            totalMaxScore.value += questionList.scores[i]
                        }
                        totalScore.value += selectedOption
                        selectedOption = 0
//                        Log.d("totalScore value", totalScore.value.toString())
                        if (count.intValue < threshold){
                            count.intValue +=1
                        }
                        else {
                            navController.navigate("completed_screen")
                        }
                    },
                    modifier = modifier
                        .width(150.dp)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    Lab03Theme {
        val navController = rememberNavController()
        val userInput = remember { mutableStateOf("") }
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuestionScreen(Modifier, navController, userInput)
        }
    }
}
package com.example.lab03

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object StartScreen: Screen(route = "start_screen")
    object QuestionScreen: Screen(route = "question_screen")
    object CompletedScreen: Screen(route = "completed_screen")
    object CancelScreen: Screen(route = "cancel_screen")
}

@Composable
fun NavGraph (
    modifier: Modifier,
    navController: NavHostController,
    userInput: MutableState<String>,
    totalScore: MutableState<Int>,
    totalMaxScore: MutableState<Int>
) {

    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route
    ) {
        composable(Screen.StartScreen.route) {
            StartScreen(
                navController = navController,
                userInput = userInput,
                modifier = modifier
            )
        }
        composable(Screen.QuestionScreen.route) {
            QuestionScreen(
                navController = navController,
                userInput = userInput,
                modifier = modifier,
                totalScore = totalScore,
                totalMaxScore = totalMaxScore
            )
        }
        composable(Screen.CompletedScreen.route) {
            CompletedScreen(
                navController = navController,
                userInput = userInput,
                modifier = modifier,
                totalMaxScore = totalMaxScore,
                totalScore = totalScore
            )
        }
    }
}
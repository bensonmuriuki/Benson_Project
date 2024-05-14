package com.example.benson_project.ui.theme.Screens.vote

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun VotePage(navController: NavHostController) {
    var voteCount by remember { mutableStateOf(0) }
    var hasVoted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Vote for your favorite!")
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "You have voted $voteCount times.")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            voteCount++
            hasVoted = true

            // Navigate to the result page and pass the voteCount value
            navController.navigate("resultPage") {
                popUpTo("votePage") { inclusive = true }
                launchSingleTop = true
                // Pass the voteCount value to the result page
                navController.currentBackStackEntry?.arguments?.putInt("voteCount", voteCount)
            }
        }) {
            Text(text = "Vote")
        }
    }
}
@Preview
@Composable
fun VotePage() {
    VotePage(rememberNavController())
}
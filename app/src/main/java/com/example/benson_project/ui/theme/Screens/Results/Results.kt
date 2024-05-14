package com.example.benson_project.ui.theme.Screens.Results

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ResultPage(navController: NavHostController, voteCount: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Thank you for voting!")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You have voted $voteCount times.")

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Back")
        }
    }
}
@Preview
@Composable
fun ResultPage(){
    ResultPage()
}

package com.example.benson_project.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DontHaveAccountRow(
    onSignUpTap: () -> Unit = {},
){
    Row (
        modifier = Modifier.padding(top=12.dp, bottom = 52.dp)
    ){
        Text(text = "Don't have an Account?",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.White,
            )
        )
        Text(text = "Sign Up",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight(800),
                color = Color.White
            ),
            modifier = Modifier.clickable {
                onSignUpTap()
            }
        )

    }
}
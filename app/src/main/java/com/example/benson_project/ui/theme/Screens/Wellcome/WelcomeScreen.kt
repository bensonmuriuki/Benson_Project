package com.example.benson_project.ui.theme.Screens.Wellcome


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.benson_project.R
import com.example.benson_project.components.CButton
import com.example.benson_project.components.DontHaveAccountRow
import com.example.benson_project.navigation.ROUTE_LOGIN
import com.example.benson_project.navigation.ROUTE_REGISTER


@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier)
{
    Box (modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.frontend),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
                .matchParentSize())

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ){

            Text(text = "Welcome To ",
                fontSize = 32.sp,
                fontWeight = FontWeight(900),
                color = Color.Blue,
                fontFamily = FontFamily.Cursive
            )
            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)


                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.weight(0.1f))
            Text(text = "Stay and Enjoy.\nLive a Life to Remember." +
                    "With  Easy-VoteHub",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            CButton(text = "Sign In With Email",
                onClick = {
                    navController.navigate(ROUTE_LOGIN)

                })

            DontHaveAccountRow(
                onSignUpTap = {
                    navController.navigate(ROUTE_REGISTER)
                }
            )
        }

    }

}
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(rememberNavController())
}
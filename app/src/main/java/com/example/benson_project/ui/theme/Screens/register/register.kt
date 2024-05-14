package com.example.benson_project.ui.theme.Screens.register



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.mandatorySystemGesturesPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.benson_project.R
import com.example.benson_project.data.AuthViewModel
import com.example.benson_project.navigation.ROUTE_ABOUT
import com.example.benson_project.navigation.ROUTE_HOME
import com.example.benson_project.navigation.ROUTE_LOGIN
import com.example.benson_project.navigation.ROUTE_REGISTER
import com.example.benson_project.navigation.ROUTE_VIEW_NOMINEE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.register),
            contentDescription = "register page",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize().mandatorySystemGesturesPadding()


        )

        var email by remember { mutableStateOf(TextFieldValue("")) }
        var pass by remember { mutableStateOf(TextFieldValue("")) }
        var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
        var context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize(),
//        .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            val context = LocalContext.current.applicationContext
            TopAppBar(title = { Text(text = "E-VoteHub") },
                navigationIcon = {


                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "/E-VoteHub/"
                        )
//        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ), actions = {

                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME)
                        Toast.makeText(
                            context,
                            "You have clicked  Home button",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(ROUTE_ABOUT)
                        Toast.makeText(
                            context,
                            "You have clicked  about Page",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(ROUTE_REGISTER)
                        Toast.makeText(
                            context,
                            "You have clicked a profile",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "My profile",
                            tint = Color.White
                        )
                    }
                }
            )
//            Text(
//                text = "Register here",
//                color = Color.Cyan,
//                fontFamily = FontFamily.SansSerif,
//                fontSize = 30.sp
//            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text(text = "Enter Email") },

                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(value =pass , onValueChange = {pass=it},
//            label = { Text(text = "Enter Gender") },
//            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        )

            OutlinedTextField(
                value = pass, onValueChange = { pass = it },
                label = { Text(text = "Enter password") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = confirmpass, onValueChange = {
                    confirmpass = it
                },
                label = { Text(text = "Enter Confirm Pass") },

                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))


            Button(onClick = {
                val myregister = AuthViewModel(navController, context)
                myregister.signup(email.text.trim(), pass.text.trim(), confirmpass.text.trim())
                navController.navigate(ROUTE_VIEW_NOMINEE)


            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Register ")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                navController.navigate(ROUTE_LOGIN)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Have an Account? Click to Login")
            }

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())

}

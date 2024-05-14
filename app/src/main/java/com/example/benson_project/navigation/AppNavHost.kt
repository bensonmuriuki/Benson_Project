package com.example.benson_project.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.benson_project.nominee.AddNomineeScreen
import com.example.benson_project.nominee.UpdateNomineeScreen
import com.example.benson_project.nominee.ViewNomineeScreen
import com.example.benson_project.nominee.ViewUploadsScreen
import com.example.benson_project.ui.theme.Screens.Results.ResultPage
import com.example.benson_project.ui.theme.Screens.Wellcome.WelcomeScreen
import com.example.benson_project.ui.theme.Screens.aboutPage.AboutScreen
import com.example.benson_project.ui.theme.Screens.homeScreen.HomeScreen
import com.example.benson_project.ui.theme.Screens.login.LoginScreen
import com.example.benson_project.ui.theme.Screens.register.RegisterScreen
import com.example.benson_project.ui.theme.Screens.vote.VotePage


@Composable
fun AppNavHost(modifier: Modifier=Modifier, navController: NavHostController= rememberNavController(),startDestination: String= ROUTE_LOGIN) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_ADD_NOMINEE) {
            AddNomineeScreen(navController)
        }

    composable(ROUTE_UPDATE_NOMINEE + "/{id}") { passedData ->
        UpdateNomineeScreen(navController, passedData.arguments?.getString("id")!!)
    }
    composable(ROUTE_VIEW_NOMINEE) {
        ViewNomineeScreen(navController)
    }
    composable(ROUTE_VIEW_UPLOAD) {
        ViewUploadsScreen(navController)
    }
    composable(ROUTE_VOTE) {
                VotePage(navController)
            }
    composable(ROUTE_RESULT) {
                ResultPage()
            }
        composable(ROUTE_WELCOME) {
            WelcomeScreen(navController)
        }

    }
}
package com.example.benson_project.ui.theme.Screens.aboutPage


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.mandatorySystemGesturesPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.benson_project.R
import com.example.benson_project.navigation.ROUTE_HOME


@Composable
    fun AboutScreen(navController: NavController) {

    Box {
        Image(
            painter = painterResource(id = R.drawable.about),
            contentDescription = "home page",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize().mandatorySystemGesturesPadding()


        )
        Column(modifier = Modifier.verticalScroll(
            rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),

            ) {
                Text(modifier = Modifier, color = Color.Blue,
                    text = " About Easy-VoteHub \n" +
                            "\n" +
                            "Welcome to Easy-VoteHub, the secure and convenient way to participate in online voting!\n" +
                            "\n" +
                            "Version: [Version Number]\n" +
                            "\n" +
                            "Developed by: [ Easy-VoteHub]\n" +
                            "\n" +
                            "Website: [Your Company Website URL]\n" +
                            "\n" +
                            "Contact: [bboybemn480@gmail.com /call Us on  0728716511]\n" +
                            "\n" +
                            "**About Us:**\n" +
                            "\n" +
                            " Easy-VoteHub is dedicated to providing a reliable platform for conducting online voting processes. Our mission is to make voting accessible to everyone while ensuring the integrity and security of the voting process.\n" +
                            "\n" +
                            "**Features:**\n" +
                            "\n" +
                            "- Secure authentication and voting process\n" +
                            "- User-friendly interface for easy navigation\n" +
                            "- Real-time updates on voting results\n" +
                            "- Accessibility features for all users\n" +
                            "- Privacy protection and data security measures\n" +
                            "\n" +
                           " Acknowledgments and Credits \n" +

                           " I extend my sincere appreciation to the individuals and resources that contributed to the development of Easy-VoteHub. While this project was largely a solo endeavor, it would not have been possible without the support and assistance of various entities. \n" +

                            "Development: \n" +

                       " I BENSON MURIUKI : Developer and creator of Easy-VoteHub.\n" +

                    "Advisors and Mentors:\n" +

               " [Advisor/Mentor Name]: For providing invaluable guidance and advice throughout the development process.\n" +

                "Testers:\n" +

               " A special thanks to those who volunteered their time to test and provide feedback on Easy-VoteHub.\n" +

                "Open Source Contributions:\n" +

               " I acknowledge and appreciate the creators and maintainers of the open-source libraries and tools utilized in the development of Easy-VoteHub.\n" +

               " Supportive Community:\n" +

                "I am grateful for the online communities and forums where I found support, advice, and inspiration during the development journey.\n" +

                "Special Thanks:\n" +

               " I offer my heartfelt thanks to my family and friends for their unwavering support and encouragement throughout this project.\n" +

               " Legal Compliance:\n" +

               "Easy-VoteHub complies with all relevant laws and regulations governing online voting. For inquiries regarding legal matters, please contact [BBOYBEN480@gmail.com / Call US on 0728716511].\n")
            }

            Button(
                onClick = {
                    navController.navigate(ROUTE_HOME)
                }, modifier = Modifier
            ) {
                Text(text = "Back Home")

            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
        AboutScreen(rememberNavController())

    }

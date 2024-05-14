package com.example.benson_project.nominee


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.benson_project.R
import com.example.benson_project.data.NomineeViewModel
import com.example.benson_project.models.Nominee
import com.example.benson_project.navigation.ROUTE_UPDATE_NOMINEE


@Composable
fun ViewNomineeScreen(navController:NavHostController) {
    Box (modifier = Modifier.fillMaxSize())
    {
        Image(painter = painterResource(id = R.drawable.iconpic),
            contentDescription ="Background Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current
        val nomineeRepository = NomineeViewModel(navController, context)
        val emptyNomineeState = remember { mutableStateOf(Nominee("","","","")) }
        val emptyNomineeListState = remember { mutableStateListOf<Nominee>() }

        val nominees = nomineeRepository.viewNominees(emptyNomineeState, emptyNomineeListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All Nominees",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(nominees){
                    NomineeItem(
                        name = it.name,
                        email = it.email,
                        post = it.post,
                        id = it.id,
                        navController = navController,
                        nomineeRepository = nomineeRepository
                    )
                }
            }
        }
    }
}



@Composable
fun NomineeItem(name:String, email:String, post:String, id:String,
                navController:NavHostController, nomineeRepository: NomineeViewModel
) {

    Column(modifier = Modifier.fillMaxWidth() ) {
        Text(text = name)
        Text(text = email)
        Text(text = post)
        Button(onClick = {
            nomineeRepository.deleteNominee(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_NOMINEE+"/$id")
        }) {
            Text(text = "Update")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun view() {
    ViewNomineeScreen(rememberNavController())

}





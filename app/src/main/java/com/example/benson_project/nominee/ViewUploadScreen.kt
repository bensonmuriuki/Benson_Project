package com.example.benson_project.nominee


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.benson_project.R
import com.example.benson_project.data.NomineeViewModel
import com.example.benson_project.models.Upload
import com.example.benson_project.navigation.ROUTE_UPDATE_NOMINEE



@Composable
fun ViewUploadsScreen(navController:NavHostController) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.v),
            contentDescription = "home page",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .mandatorySystemGesturesPadding()


        )}

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var nomineeRepository = NomineeViewModel(navController, context)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        var uploads = nomineeRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All uploads",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(uploads){
                    UploadItem(
                        name = it.name,
                        email = it.email,
                        post = it.post,
                        imageUrl = it.imageUrl,
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
fun UploadItem(name:String, email:String, post:String, imageUrl:String, id:String,
               navController:NavHostController, nomineeRepository: NomineeViewModel
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Card ( modifier = Modifier
            .height(100.dp)
            .width(120.dp)
            .align(Alignment.CenterHorizontally)){
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier =
                Modifier.size(128.dp).height(90.dp).width(400.dp)
            )

        }

        }
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



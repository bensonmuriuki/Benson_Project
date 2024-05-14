package com.example.benson_project.nominee


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.benson_project.data.NomineeViewModel
import com.example.benson_project.models.Nominee
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNomineeScreen(navController: NavHostController, id: String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var post by remember { mutableStateOf("") }

        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Nominee/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var nominee = snapshot.getValue(Nominee::class.java)
                name = nominee!!.name
                email = nominee!!.email
                post = nominee!!.post
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "Add New Nominee",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var nomeneeName by remember { mutableStateOf(TextFieldValue(name)) }
        var nomineeEmail by remember { mutableStateOf(TextFieldValue(email)) }
        var nomineePost by remember { mutableStateOf(TextFieldValue(post)) }

        OutlinedTextField(
            value = nomeneeName,
            onValueChange = { nomeneeName = it },
            label = { Text(text = "Nominee name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nomineeEmail,
            onValueChange = { nomineeEmail = it },
            label = { Text(text = "nominee Email *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nomineePost,
            onValueChange = { nomineePost = it },
            label = { Text(text = " nominee Post *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var productRepository = NomineeViewModel(navController, context)
            productRepository.updateNominee(nomeneeName.text.trim(),nomineeEmail.text.trim(),
                nomineePost.text.trim(), id = "")


        }) {
            Text(text = "Update")
        }

    }
}

@Preview
@Composable
fun update() {
    UpdateNomineeScreen(rememberNavController(), id = "")
}
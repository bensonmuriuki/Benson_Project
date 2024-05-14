package com.example.benson_project.data


import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.benson_project.models.Nominee
import com.example.benson_project.models.Upload
import com.example.benson_project.navigation.ROUTE_LOGIN
import com.example.benson_project.navigation.ROUTE_VIEW_UPLOAD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class NomineeViewModel(var navController: NavHostController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveNominee(nomineeName: String, nomineeEmail: String, nomineePost: String) {
        var id = System.currentTimeMillis().toString()
        var nomineeData = Nominee(nomineeName, nomineeEmail, nomineePost,id)
        var nomineeRef = FirebaseDatabase.getInstance().getReference()
            .child("Nominee/$id")
        progress.show()
        nomineeRef.setValue(nomineeData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewNominees(
        nominee: MutableState<Nominee>,
        nominees: SnapshotStateList<Nominee>
    ): SnapshotStateList<Nominee> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Nominees")

        //progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //progress.dismiss()
                nominees.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Nominee::class.java)
                    nominee.value =value!!
                    nominees.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return nominees
    }



    fun deleteNominee(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("nominees/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Nominee deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateNominee(name: String, email: String, post: String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("nominees/$id")
        progress.show()
        var updateData = Nominee(name, email, post, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveNomineeWithImage(nomineeName:String, nomineeEmail:String, nomineePost:String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = Upload(nomineeName,nomineeEmail,
                        nomineePost,imageUrl,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Uploads/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_VIEW_UPLOAD)
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun viewUploads(upload:MutableState<Upload>, uploads:SnapshotStateList<Upload>): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }
}

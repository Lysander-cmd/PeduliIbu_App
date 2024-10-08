package com.example.peduliibu_app.Database

import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserDatabase {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var db: DatabaseReference

    fun createUsername(username: String) {
        db =  FirebaseDatabase.getInstance().reference
        db.child("users").child(auth.currentUser?.uid.toString()).child("username")
            .setValue(username)
    }

    fun readUsername(userId: String, textView: TextView) {
        db  = FirebaseDatabase.getInstance().reference
        val transaksiRef = db.child("users")
        transaksiRef.child(userId).get().addOnSuccessListener {
            if (it.exists()) {
                val username = it.child("username").value
                textView.text = username.toString()
            } else {
                textView.text = "NULL"
            }
        }.addOnFailureListener {
            textView.text = "went wrong"
        }
    }
}
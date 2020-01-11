package com.android.rbcanalytica.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class  AnalysisRepository  {
    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    fun getTwitterResults() : CollectionReference{
        return firestoreDB.collection("twitter")
    }
}
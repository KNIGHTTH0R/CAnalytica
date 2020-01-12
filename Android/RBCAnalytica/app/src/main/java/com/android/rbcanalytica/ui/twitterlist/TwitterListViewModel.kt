package com.android.rbcanalytica.ui.twitterlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rbcanalytica.repository.FirebaseRepository
import com.android.rbcanalytica.repository.Review
import javax.inject.Inject

class TwitterListViewModel @Inject constructor() : ViewModel() {

    private val TAG = "FIRESTORE_VIEW_MODEL"
    private val analysisRepository = FirebaseRepository()
    var reviews: MutableLiveData<List<Review>> = MutableLiveData()

    fun getReviews(): LiveData<List<Review>> {
        val reviewsUpdate:ArrayList<Review> = ArrayList()
        analysisRepository.getTwitterResults()
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val review = document.toObject(Review::class.java)
                    reviewsUpdate.add(review)
                    Log.d(TAG, "$review")
                }
                reviews.value = reviewsUpdate
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return reviews
    }
}
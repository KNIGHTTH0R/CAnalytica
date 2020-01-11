package com.android.rbcanalytica.ui.reviewanalysis

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rbcanalytica.repository.AnalysisRepository
import com.android.rbcanalytica.repository.Review
import javax.inject.Inject

class ReviewAnalysisViewModel @Inject constructor() : ViewModel() {

    val TAG = "FIRESTORE_VIEW_MODEL"
    val analysisRepository = AnalysisRepository()
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
                reviews.value  = reviewsUpdate
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return reviews
    }
}
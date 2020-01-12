package com.android.rbcanalytica.repository

import com.google.firebase.firestore.PropertyName
import java.util.Date

data class Review(

    var id: String = "",

    @get:PropertyName("phrase")
    @set:PropertyName("phrase")
    var reviewText: String = "",

    @get:PropertyName("technical")
    @set:PropertyName("technical")
    var technical: Boolean = false,

    @get:PropertyName("positive")
    @set:PropertyName("positive")
    var positive: Boolean = false,

    @get:PropertyName("Date")
    @set:PropertyName("Date")
    var date: Date = Date(0)

) {
    override fun toString(): String {
        return "{ ID: ${this.id}, Text: ${this.reviewText}, Technical: ${this.technical}, Positive: ${this.positive}, Date: ${this.date} }"
    }
}
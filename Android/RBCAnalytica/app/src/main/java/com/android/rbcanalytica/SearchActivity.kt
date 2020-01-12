package com.android.rbcanalytica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.rbcanalytica.repository.FirebaseRepository
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchButton.setOnClickListener { nextScreen() }
    }

    private fun nextScreen() {
        val hashtag = hashtagInput.text.toString()
        FirebaseRepository().postHashtag(hashtag)
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("EXTRA_HASHTAG", hashtag)
        startActivity(intent)
    }
}

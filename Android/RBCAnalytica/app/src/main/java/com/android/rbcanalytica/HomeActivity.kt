package com.android.rbcanalytica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        facebookButton.setOnClickListener { navigateToFacebookScreen() }
        twitterButton.setOnClickListener { navigateToTwitterScreen() }
        redditButton.setOnClickListener { navigateToRedditScreen() }
    }

    private fun navigateToRedditScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTwitterScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFacebookScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

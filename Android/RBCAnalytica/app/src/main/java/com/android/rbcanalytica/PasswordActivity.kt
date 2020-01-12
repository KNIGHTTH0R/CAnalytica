package com.android.rbcanalytica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        nextButton.setOnClickListener { nextScreen() }
    }

    private fun nextScreen() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}

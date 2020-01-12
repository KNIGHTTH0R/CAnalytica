package com.android.rbcanalytica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.rbcanalytica.databinding.ActivityWelcomeBinding
import kotlinx.android.synthetic.main.activity_email.*
import kotlinx.android.synthetic.main.activity_name.*
import kotlinx.android.synthetic.main.activity_name.nextButton

class EmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        nextButton.setOnClickListener { nextScreen() }
    }

    private fun nextScreen() {
        val intent = Intent(this, PasswordActivity::class.java)
        startActivity(intent)
    }
}

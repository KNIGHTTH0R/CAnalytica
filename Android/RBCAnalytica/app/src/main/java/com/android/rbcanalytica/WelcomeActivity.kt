package com.android.rbcanalytica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        getStartedButton.setOnClickListener{switchScreens()}
    }

    private fun switchScreens(){
        val intent = Intent(this, NameActivity::class.java)
        startActivity(intent)
    }
}

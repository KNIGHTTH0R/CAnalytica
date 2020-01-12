package com.android.rbcanalytica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.rbcanalytica.databinding.ActivityNameBinding
import com.android.rbcanalytica.databinding.ActivityWelcomeBinding
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        nextButton.setOnClickListener{nextScreen()}
    }

    private fun nextScreen(){
        val intent = Intent(this, EmailActivity::class.java)
        startActivity(intent)
    }
}

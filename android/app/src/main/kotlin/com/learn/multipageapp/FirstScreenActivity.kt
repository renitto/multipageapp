package com.learn.multipageapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FirstScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        // get reference to button
        val btn_navigate_to_flutter_screen = findViewById<Button>(R.id.button)
        // set on-click listener
        btn_navigate_to_flutter_screen.setOnClickListener {
            Toast.makeText(
                this@FirstScreenActivity,
                "Navigating to flutter screen",
                Toast.LENGTH_LONG
            ).show()

//            return to MainActivity
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent) // OK! (use whatever code you want)
            finish()
        }
    }
}
package com.example.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val helloTextView: TextView = findViewById(R.id.division_textview)
        //helloTextView.text = "Hello, debugging!"
        logging()
        division()
    }

    fun logging() {
        Log.v(TAG, "Hello, World!")
    }

    fun division() {
        val numerator = 60
        var denominator = 4
        repeat(4) {
            Thread.sleep(3)
            findViewById<TextView>(R.id.division_textview).text = "${numerator / denominator}"
            denominator--
        }
    }
}
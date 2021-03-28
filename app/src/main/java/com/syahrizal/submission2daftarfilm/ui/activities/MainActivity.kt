package com.syahrizal.submission2daftarfilm.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syahrizal.submission2daftarfilm.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f
    }
}
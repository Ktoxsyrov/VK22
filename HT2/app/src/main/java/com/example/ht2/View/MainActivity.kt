package com.example.ht2.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ht2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.container, MoviesFragment())
            .commit()
    }
}
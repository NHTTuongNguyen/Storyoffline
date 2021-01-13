package com.example.projectappdoctruyen.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.projectappdoctruyen.Activity.WelcomeActivity
import com.example.projectappdoctruyen.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
        Handler().postDelayed({
            val intent = Intent(this@WelcomeActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN = 1000
    }
}
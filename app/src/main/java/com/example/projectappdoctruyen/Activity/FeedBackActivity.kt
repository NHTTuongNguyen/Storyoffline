package com.example.projectappdoctruyen.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectappdoctruyen.R
import com.google.android.gms.ads.AdView

class FeedBackActivity : AppCompatActivity() {
    private var adView: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)
        adView = findViewById(R.id.adView)
    }
}
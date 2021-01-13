package com.example.projectappdoctruyen.Activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.projectappdoctruyen.Adapter.ViewPagerHomeAdapter
import com.example.projectappdoctruyen.Fragment.AboutFragment
import com.example.projectappdoctruyen.Fragment.HistoryFragment
import com.example.projectappdoctruyen.Fragment.SearchFragment
import com.example.projectappdoctruyen.Fragment.StoryFragment
import com.example.projectappdoctruyen.R
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener
import com.google.android.gms.ads.AdView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var bottomNavigationView: BottomNavigationView? = null
    private val frameLayout: FrameLayout? = null
    private val fragmentTransaction: FragmentTransaction? = null
    private var historyFragment: HistoryFragment? = null
    private var aboutFragment: AboutFragment? = null
    private var searchFragment: SearchFragment? = null
    private var storyFragment: StoryFragment? = null
    private val mAdView: AdView? = null
    private val TAG = HomeActivity::class.java.simpleName
    private var interstitialAd: InterstitialAd? = null
    private var viewPager: ViewPager? = null
    var menuItem: MenuItem? = null
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigationView!!.setOnNavigationItemSelectedListener(this)
        //        permission_WRITE_SETTINGS(200);
//        initInterstitialAd();
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                if (menuItem != null) {
                    menuItem!!.isChecked = false
                } else {
                    bottomNavigationView!!.menu.getItem(0).isChecked = false
                }
                Log.d("pagess", "onPageSelected: $position")
                bottomNavigationView!!.menu.getItem(position).isChecked = true
                menuItem = bottomNavigationView!!.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        setupViewPager(viewPager)
        //        hasPermissionToReadNetworkHistory();
    }

    private fun payUsers(a: Int): Int {
        if (a in 1..50) {
            return 600 * a
        } else if (a in 51..200) {
            return 50 * 600 + (a - 50) * 400
        } else if (a > 200) {
            return 50 * 600 + 150 * 400 + (a - 200) * 200
        }
        return 0
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerHomeAdapter(supportFragmentManager)
        searchFragment = SearchFragment()
        storyFragment = StoryFragment()
        historyFragment = HistoryFragment()
        aboutFragment = AboutFragment()
        adapter.addFragment(storyFragment!!)
        adapter.addFragment(searchFragment!!)
        adapter.addFragment(historyFragment!!)
        adapter.addFragment(aboutFragment!!)
        viewPager!!.adapter = adapter
    }

    private fun permission_WRITE_SETTINGS(requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(applicationContext)) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:$packageName"))
                startActivityForResult(intent, requestCode)
            }
        }
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Exit")
        alertDialogBuilder
                .setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes"
                ) { dialog, id -> finishAffinity() }
                .setNegativeButton("No") { dialog, id -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                viewPager!!.currentItem = 0
                true
            }
            R.id.navigation_notifications -> {
                viewPager!!.currentItem = 1
                true
            }
            R.id.navigation_history -> {
                viewPager!!.currentItem = 2
                true
            }
            R.id.navigation_about -> {
                viewPager!!.currentItem = 3
                true
            }
            else -> false
        }
    }

    private fun initInterstitialAd() {
        interstitialAd = InterstitialAd(this, "IMG_16_9_APP_INSTALL#" + idInterstitialAd)
        // Set listeners for the Interstitial Ad
        interstitialAd!!.setAdListener(object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.")
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.")
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.errorMessage)
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!")
                // Show the ad
                interstitialAd!!.show()
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!")
            }
        })

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd!!.loadAd()
    }

    companion object {
        private const val idInterstitialAd = "723868771524475_728413764403309"
        private const val idAdView = "723868771524475_723869634857722"
    }
}
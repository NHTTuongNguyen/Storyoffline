package com.example.projectappdoctruyen.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.projectappdoctruyen.Adapter.ViewPagerHomeAdapter;
import com.example.projectappdoctruyen.Fragment.AboutFragment;
import com.example.projectappdoctruyen.Fragment.HistoryFragment;
import com.example.projectappdoctruyen.Fragment.SearchFragment;
import com.example.projectappdoctruyen.Fragment.StoryFragment;
import com.example.projectappdoctruyen.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;

    private HistoryFragment historyFragment;
    private AboutFragment aboutFragment;
    private SearchFragment searchFragment;
    private StoryFragment storyFragment;
    private AdView mAdView;
    private final String TAG = HomeActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;
    private static final String idInterstitialAd="723868771524475_728413764403309";
    private static final String idAdView = "723868771524475_723869634857722";
    private ViewPager viewPager;
    MenuItem menuItem;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        permission_WRITE_SETTINGS(200);
//        initInterstitialAd();
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("pagess", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
//        hasPermissionToReadNetworkHistory();


    }
    private int payUsers(int a){
        if (a >=1 && a <=50){
            return 600 * a ;
        }else if (a>50 &&  a <=200){
            return (50*600) +((a -50 )*400);
        }else if (a >200){
            return (50*600) + (150*400) + (a - 200) * 200 ;
        }
        return 0;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getSupportFragmentManager());
        searchFragment = new SearchFragment();
        storyFragment = new StoryFragment();
        historyFragment = new HistoryFragment();
        aboutFragment = new AboutFragment();
        adapter.addFragment(storyFragment);
        adapter.addFragment(searchFragment);
        adapter.addFragment(historyFragment);
        adapter.addFragment(aboutFragment);
        viewPager.setAdapter(adapter);
    }

    private void permission_WRITE_SETTINGS(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, requestCode);
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder
                .setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finishAffinity();
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_notifications:
                viewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_history:
                viewPager.setCurrentItem(2);
                return true;
            case R.id.navigation_about:
                viewPager.setCurrentItem(3);
                return true;
            default:
                return false;
        }
    }
    private void initInterstitialAd() {
        interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#"+idInterstitialAd);
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();
    }

}

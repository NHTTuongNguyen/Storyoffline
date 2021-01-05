package com.example.projectappdoctruyen.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectappdoctruyen.Adapter.HistoryFragmentsAdapter;
import com.example.projectappdoctruyen.Model.Story;
import com.example.projectappdoctruyen.R;
import com.example.projectappdoctruyen.Service.CheckConnection;
import com.example.projectappdoctruyen.SettingSharedPreferences.SharedPreferences_Utils;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    private View view;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private HistoryFragmentsAdapter adapter;
    private ArrayList<Story> storyList;
    private TextView txtNodataHistory;
    private SharedPreferences_Utils sharedPreferencesUtils;
    private TemplateView templateView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        initView();
        storyList =  sharedPreferencesUtils.get_SharedPreferences_Story_HistoryFragment();
        buildRecyclerview();
        checkConnectionAds();
        return view;
    }

    private void initView() {
        sharedPreferencesUtils = new SharedPreferences_Utils(getActivity());
        txtNodataHistory = view.findViewById(R.id.txtNodataHistory);
        recyclerView = view.findViewById(R.id.recyclerView_Docganday);
        templateView =view.findViewById(R.id.template_HistoryFragment);
    }

    private void checkConnectionAds() {
        if (CheckConnection.isConnectedtoInternet(getActivity())){
            setAdsView();
        }else {
            templateView.setVisibility(View.GONE);
        }
    }

    private void setAdsView() {
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdLoader.Builder builder = new AdLoader.Builder(getActivity(),getString(R.string.Ads_appId));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                templateView.setNativeAd(unifiedNativeAd);
            }
        });
        AdLoader adLoader  = builder.build();
        AdRequest adRequest = new AdRequest.Builder().build();
        adLoader.loadAd(adRequest);
    }
    private void buildRecyclerview() {
        if (storyList.size()>0) {
            adapter = new HistoryFragmentsAdapter(getActivity(), storyList);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            txtNodataHistory.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }else {
            txtNodataHistory.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

}
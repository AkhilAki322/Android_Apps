package com.akhil.ecomputers;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.akhil.ecomputers.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class splash extends AppCompatActivity {
    private WebView webView;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        webView = (WebView)findViewById(R.id.webvw);


        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-8845455376910681~4819171788");
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // DO NOT CALL SUPER METHOD
                super.onReceivedSslError(view, handler, error);
            }
        });
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.ecomputers.co.in/");

        webView.clearCache(true);

        InterstitialAd interstitialAd;
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8845455376910681/8786336951");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.show();

    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}

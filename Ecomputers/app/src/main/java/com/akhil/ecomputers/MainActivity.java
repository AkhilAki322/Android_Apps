package com.akhil.ecomputers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.akhil.ecomputers.R;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, splash.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH);
    }
}
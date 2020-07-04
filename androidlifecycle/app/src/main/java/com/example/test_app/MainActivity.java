package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onstop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onrestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ondestroy");
    }
}

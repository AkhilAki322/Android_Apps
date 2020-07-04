package com.example.background;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.SystemUpdatePolicy;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button butt;
    Button button;
    private volatile boolean stopthre = false;
//    private Handler hand = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butt = findViewById(R.id.startbutton);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),MyService.class));
            }
        });
    }
    public void startthread(View view) {
//        examplethread thred = new examplethread();
//        thred.start();
        System.out.println("start");
        stopthre = false;
        examplerunnable runn = new examplerunnable();
        new Thread(runn).start();
    }
    public void stopthread(View view){
        stopthre = true;
        System.out.println("stop");
    }

    class examplethread extends Thread {
        @Override
        public void run() {
            for(int i=0; i<10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("start " + i);
            }
        }
    }
    class examplerunnable implements Runnable {
        @Override
        public void run() {
            for(int i=0; i<10; i++){
                if (stopthre){
                    return;}
                if (i==5){
//                    **********
//                    Handler hand = new Handler(Looper.getMainLooper());
//                    hand.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            butt.setText("50%");
//                        }
//                    });
//                    *********
//                    **********
//                    butt.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            butt.setText("50%");
//                        }
//                    });
//                    *************
//                    ***********
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            butt.setText("50%");
//                        }
//                    });
//                    **********
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("start run " + i);
            }
        }
    }
}

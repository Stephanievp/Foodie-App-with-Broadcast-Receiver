package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    protected static final String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendTheBroadcast(View view) {
        Log.d(TAG, "Button Clicked: Sending Custom Broadcast");

        Intent intent = new Intent();
        intent.setAction(ACTION_CUSTOM_BROADCAST);
        sendBroadcast(intent);

    }
}

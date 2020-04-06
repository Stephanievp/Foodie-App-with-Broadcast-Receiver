package com.example.foodieapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MyReceiver extends BroadcastReceiver {

    protected static final String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";
    private static final String TAG = "MyReceiver";
    private ArrayList<MealItem> mealData;

    MyReceiver(ArrayList<MealItem> mealData){
        this.mealData = mealData;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if(intentAction == ACTION_CUSTOM_BROADCAST){
            Log.d(TAG, "Custom Broadcast Received");

            Random random = new Random();
            int mealLocation = random.nextInt(mealData.size());
            Log.d(TAG, "mealLocation = " + mealLocation);

            MealItem currentMeal = mealData.get(mealLocation);
            Intent myIntent = new Intent(context, MealDetailActivity.class);
            myIntent.putExtra("title", currentMeal.getTitle());
            myIntent.putExtra("description", currentMeal.getDescription());
            myIntent.putExtra("image_resource", currentMeal.getImageId());
            myIntent.putExtra("link", currentMeal.getMeal_info());
            context.startActivity(myIntent);

            Toast.makeText(context, "Happy Cooking " + currentMeal.getTitle(), Toast.LENGTH_LONG).show();

        }

    }
}

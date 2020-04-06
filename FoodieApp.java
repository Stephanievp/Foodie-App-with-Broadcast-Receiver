package com.example.foodieapp;

import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MealItem> mealData;
    private MealItemAdapter mealItemAdapter;

    private int gridColumnCount;

    private MyReceiver myReceiver;
    protected static final String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mealData = new ArrayList<>();
        mealItemAdapter = new MealItemAdapter(this, mealData);
        recyclerView.setAdapter(mealItemAdapter);

        loadMealData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CUSTOM_BROADCAST);

        myReceiver = new MyReceiver(mealData);
        this.registerReceiver(myReceiver, filter);

    }

    private void loadMealData() {

        // Get the resources from the XML file.
        String[] mealTitle = getResources()
                .getStringArray(R.array.meal_titles);
        String[] mealDesc = getResources()
                .getStringArray(R.array.meal_descriptions);
        TypedArray mealImageResources = getResources()
                .obtainTypedArray(R.array.meal_images);
        String[] mealInfo = getResources()
                .getStringArray(R.array.meal_info);

        // Clear the existing data (to avoid duplication).
        mealData.clear();

        for(int i = 0; i < mealTitle.length; i++){
            mealData.add(new MealItem(mealTitle[i],mealDesc[i],
                    mealImageResources.getResourceId(i,0), mealInfo[i]));
        }

        // Recycle the typed array.
        mealImageResources.recycle();

        // Notify the adapter of the change.
        mealItemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(myReceiver);

        super.onDestroy();
    }
}

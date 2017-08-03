package com.xiubinzheng.tummytruck.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xiubinzheng.tummytruck.R;
import com.xiubinzheng.tummytruck.adapter.FoodTruckAdapter;
import com.xiubinzheng.tummytruck.constants.Constants;
import com.xiubinzheng.tummytruck.data.DataService;
import com.xiubinzheng.tummytruck.model.FoodTruck;
import com.xiubinzheng.tummytruck.view.ItemDecorator;

import java.util.ArrayList;

public class FoodTrucksListActivity extends AppCompatActivity {

    // Variables
    private FoodTruckAdapter adapter;
    private ArrayList<FoodTruck> trucks = new ArrayList<>();
    private static FoodTrucksListActivity foodTrucksListActivity;
    private FloatingActionButton addTruckBtn;
    public static final String EXTRA_ITEM_TRUCK = "TRUCK";
    SharedPreferences prefs;

    public static FoodTrucksListActivity getFoodTrucksListActivity() {
        return foodTrucksListActivity;
    }

    public static void setFoodTrucksListActivity(FoodTrucksListActivity foodTrucksListActivity) {
        FoodTrucksListActivity.foodTrucksListActivity = foodTrucksListActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_trucks_list);

        foodTrucksListActivity.setFoodTrucksListActivity(this);
        addTruckBtn = (FloatingActionButton) findViewById(R.id.addTruckBtn);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        addTruckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAddTruck();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TrucksDownloaded listener = new TrucksDownloaded() {
            @Override
            public void success(Boolean success) {
                if (success) {
                    setUpRecycler();
                }
            }
        };

        trucks = DataService.getInstance().downloadAllFoodTrucks(this, listener);
    }

    private void setUpRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_foodtruck);
        recyclerView.setHasFixedSize(true);
        adapter = new FoodTruckAdapter(trucks);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDecorator(0,0,0, 10));
    }

    public interface TrucksDownloaded {
        void success(Boolean success);
    }

    public void loadFoodTruckDetailActivity(FoodTruck truck) {
        Intent intent = new Intent(FoodTrucksListActivity.this, FoodTruckDetailActivity.class);
        intent.putExtra(FoodTrucksListActivity.EXTRA_ITEM_TRUCK, truck);
        startActivity(intent);
    }

    public void loadAddTruck() {
        if (prefs.getBoolean(Constants.IS_LOGGED_IN, false)) {
            Intent intent = new Intent(FoodTrucksListActivity.this, AddTruck.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(FoodTrucksListActivity.this, LoginActivity.class);
            Toast.makeText(getBaseContext(), "Please login to add food truck", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
}

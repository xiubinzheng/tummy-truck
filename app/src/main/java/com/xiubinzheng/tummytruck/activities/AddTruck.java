package com.xiubinzheng.tummytruck.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.xiubinzheng.tummytruck.R;
import com.xiubinzheng.tummytruck.constants.Constants;
import com.xiubinzheng.tummytruck.data.DataService;

public class AddTruck extends AppCompatActivity {

    // variables
    private EditText truckName;
    private EditText foodType;
    private EditText avgCost;
    private EditText latitude;
    private EditText longitude;
    private EditText zipcode;

    private Button addBtn;
    private Button cancelBtn;
    String authToken;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truck);

        truckName = (EditText) findViewById(R.id.new_truck_name);
        foodType = (EditText) findViewById(R.id.new_truck_food_type);
        avgCost = (EditText) findViewById(R.id.new_avg_cost);
        latitude = (EditText) findViewById(R.id.new_latitude);
        longitude = (EditText) findViewById(R.id.new_long);
        zipcode = (EditText) findViewById(R.id.new_zip);

        addBtn = (Button) findViewById(R.id.add_truck);
        cancelBtn = (Button) findViewById(R.id.cancel_truck);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        authToken = prefs.getString(Constants.AUTH_TOKEN, "Does not exist");

        final AddTruckInterface listener = new AddTruckInterface() {
            @Override
            public void success(Boolean success) {
                finish();
            }
        };

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = truckName.getText().toString();
                final String type = foodType.getText().toString();
                final Double cost = Double.parseDouble(avgCost.getText().toString());
                final Double lat = Double.parseDouble(latitude.getText().toString());
                final Double longi = Double.parseDouble(longitude.getText().toString());
                final int zip = Integer.parseInt(zipcode.getText().toString());
                DataService.getInstance().addTruck(name, type, cost, lat, longi,zip, getBaseContext(), listener, authToken);
            }
        }
        );

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public interface AddTruckInterface {
        void success(Boolean success);
    }
}

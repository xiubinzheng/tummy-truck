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
import com.xiubinzheng.tummytruck.model.FoodTruck;

public class AddReviewActivity extends AppCompatActivity {

    private EditText reviewTitle;
    private EditText reviewText;
    private Button addReviewBtn;
    private Button cancelBtn;
    private FoodTruck foodTruck;
    private String authToken;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        reviewTitle = (EditText) findViewById(R.id.review_title);
        reviewText = (EditText) findViewById(R.id.review_text);
        addReviewBtn = (Button) findViewById(R.id.add_review_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_review_btn);
        foodTruck = getIntent().getParcelableExtra(FoodTruckDetailActivity.EXTRA_ITEM_TRUCK);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        authToken = prefs.getString(Constants.AUTH_TOKEN, "Does not exist");

        final AddReviewInterface listener = new AddReviewInterface() {
            @Override
            public void success(Boolean success) {
                finish();
            }
        };

        addReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String title = reviewTitle.getText().toString();
                final String text = reviewText.getText().toString();

                DataService.getInstance().addReview(title, text, foodTruck, getBaseContext(), listener, authToken);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public interface AddReviewInterface {
        void success(Boolean success);
    }
}

package com.xiubinzheng.tummytruck.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiubinzheng.tummytruck.R;
import com.xiubinzheng.tummytruck.model.FoodTruckReview;


public class ReviewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView text;

    public ReviewHolder(View itemView) {
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.review_title);
        this.text = (TextView) itemView.findViewById(R.id.review_text);
    }

    public void updateUI(FoodTruckReview review) {
        title.setText(review.getTitle());
        text.setText(review.getText());
    }
}

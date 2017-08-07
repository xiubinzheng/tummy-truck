package com.xiubinzheng.tummytruck.model;

import android.os.Parcel;
import android.os.Parcelable;



public class FoodTruck implements Parcelable {

    private String id ;
    private String name ;
    private String foodType ;
    private Double avgCost ;
    private Double latitude ;
    private Double longitude ;
    private Double zipcode ;

    public Double getZipcode() {
        return zipcode;
    }

    public void setZipcode(Double zipcode) {
        this.zipcode = zipcode;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFoodType() {
        return foodType;
    }

    public Double getAvgCost() {
        return avgCost;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public FoodTruck(String id, String name, String foodType, Double avgCost, Double latitude, Double longitude, Double zipcode) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.avgCost = avgCost;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipcode = zipcode;
    }

    // Make parcel

    private FoodTruck(Parcel in) {
        id = in.readString();
        name = in.readString();
        foodType = in.readString();
        avgCost = in.readDouble();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(foodType);
        parcel.writeDouble(avgCost);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<FoodTruck> CREATOR = new Parcelable.Creator<FoodTruck>() {
        public FoodTruck createFromParcel(Parcel in) {
            return new FoodTruck(in);
        }

        public FoodTruck[] newArray(int size) {
            return new FoodTruck[size];
        }
    };

}

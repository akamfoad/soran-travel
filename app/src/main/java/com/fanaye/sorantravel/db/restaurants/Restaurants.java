package com.fanaye.sorantravel.db.restaurants;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurants")

public class Restaurants {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uniqueid")
    private String uniqueId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "lng")
    private double lng;

    @NonNull
    @ColumnInfo(name = "lat")
    private double lat;

    @ColumnInfo(name = "phone_no")
    private String phoneNo;

    @NonNull
    @ColumnInfo(name = "open_from")
    private String openFrom;

    @NonNull
    @ColumnInfo(name = "close_at")
    private String closeAt;

    @ColumnInfo(name = "rating")
    private Double rating;

    @ColumnInfo(name = "no_of_raters")
    private Integer noOfRaters;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "website")
    private String website;

    public Restaurants(@NonNull String uniqueId, @NonNull String name, double lng, double lat, String phoneNo, @NonNull String openFrom, @NonNull String closeAt, Double rating, Integer noOfRaters, String location, String website) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.phoneNo = phoneNo;
        this.openFrom = openFrom;
        this.closeAt = closeAt;
        this.rating = rating;
        this.noOfRaters = noOfRaters;
        this.location = location;
        this.website = website;
    }

    @NonNull
    public String getUniqueId() {
        return uniqueId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    @NonNull
    public String getOpenFrom() {
        return openFrom;
    }

    @NonNull
    public String getCloseAt() {
        return closeAt;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getNoOfRaters() {
        return noOfRaters;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }
}

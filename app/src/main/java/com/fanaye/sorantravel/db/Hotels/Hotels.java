package com.fanaye.sorantravel.db.Hotels;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hotels")

public class Hotels {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uniqueid")
    private String uniqueId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "plus_code")
    private String plusCode;

    @ColumnInfo(name = "phone_no")
    private String phoneNo;


    @ColumnInfo(name = "rating")
    private Double rating;

    @ColumnInfo(name = "no_of_raters")
    private Integer noOfRaters;

    @NonNull

    @ColumnInfo(name = "location")
    private String location;

    @NonNull
    @ColumnInfo(name = "prices")
    private String prices;

    @ColumnInfo(name = "website")
    private String website;

    public Hotels(@NonNull String uniqueId, @NonNull String name, @NonNull String plusCode, String phoneNo, Double rating, Integer noOfRaters, @NonNull String location, @NonNull String prices, String website) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.plusCode = plusCode;
        this.phoneNo = phoneNo;
        this.rating = rating;
        this.noOfRaters = noOfRaters;
        this.location = location;
        this.prices = prices;
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

    @NonNull
    public String getPlusCode() {
        return plusCode;
    }

    public String getPhoneNo() {
        return phoneNo;
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

    @NonNull
    public String getPrices() {
        return prices;
    }

    public String getWebsite() {
        return website;
    }
}

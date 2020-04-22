package com.fanaye.sorantravel.db.WhereToGo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "where_to_go")
public class WhereToGo {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uniqueid")
    private String uniqueId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "lng")
    private double lng;

    @ColumnInfo(name = "lat")
    private double lat;


    @NonNull
    @ColumnInfo(name = "text_info")
    private String textInfo;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;


    public WhereToGo(@NonNull String uniqueId, @NonNull String name, double lng, double lat, String textInfo, String location) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.textInfo = textInfo;
        this.location = location;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public String getName() {
        return this.name;
    }

    public double getLng() {
        return this.lng;
    }

    public double getLat() {
        return this.lat;
    }

    public String getTextInfo() {
        return this.textInfo;
    }

    public String getLocation() {
        return this.location;
    }

}

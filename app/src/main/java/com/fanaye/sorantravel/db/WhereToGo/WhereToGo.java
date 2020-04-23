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

    @NonNull
    @ColumnInfo(name = "plus_code")
    private String plusCode;

    @NonNull
    @ColumnInfo(name = "text_info")
    private String textInfo;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;


    public WhereToGo(@NonNull String uniqueId, @NonNull String name, @NonNull String plusCode, String textInfo, String location) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.plusCode = plusCode;
        this.textInfo = textInfo;
        this.location = location;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public String getName() {
        return this.name;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public String getTextInfo() {
        return this.textInfo;
    }

    public String getLocation() {
        return this.location;
    }

}

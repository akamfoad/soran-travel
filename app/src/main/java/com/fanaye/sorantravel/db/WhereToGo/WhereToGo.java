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
    @ColumnInfo(name = "plus_code")
    private String plusCode;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;


    public WhereToGo(@NonNull String uniqueId, @NonNull String plusCode, @NonNull String location) {
        this.uniqueId = uniqueId;
        this.plusCode = plusCode;
        this.location = location;
    }

    @NonNull
    public String getUniqueId() {
        return this.uniqueId;
    }

    @NonNull
    public String getPlusCode() {
        return this.plusCode;
    }

    @NonNull
    public String getLocation() {
        return this.location;
    }

}

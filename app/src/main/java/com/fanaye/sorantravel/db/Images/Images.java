package com.fanaye.sorantravel.db.Images;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "images")
public class Images {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uniqueid")
    private String uniqueId;

    @NonNull
    @ColumnInfo(name = "picture", typeAffinity = ColumnInfo.BLOB)
    private byte[] picture;

    @NonNull
    @ColumnInfo(name = "image_of_where")
    private String imageOfWhere;

    public Images(@NonNull String uniqueId, @NonNull byte[] picture, @NonNull String imageOfWhere) {
        this.uniqueId = uniqueId;
        this.picture = picture;
        this.imageOfWhere = imageOfWhere;
    }

    @NonNull
    public String getUniqueId() {
        return uniqueId;
    }

    @NonNull
    public byte[] getPicture() {
        return picture;
    }

    @NonNull
    public String getImageOfWhere() {
        return imageOfWhere;
    }
}

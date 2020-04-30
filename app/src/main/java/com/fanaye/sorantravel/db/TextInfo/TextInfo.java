package com.fanaye.sorantravel.db.TextInfo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "text_info")
public class TextInfo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uniqueid")
    private String uniqueId;

    @NonNull
    @ColumnInfo(name = "info")
    private String info;

    @NonNull
    @ColumnInfo(name = "info_of_where")
    private String info_of_where;

    @NonNull
    @ColumnInfo(name = "locale")
    private String locale;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public TextInfo(@NonNull String uniqueId, @NonNull String info, @NonNull String info_of_where, @NonNull String locale, @NonNull String name) {
        this.uniqueId = uniqueId;
        this.info = info;
        this.info_of_where = info_of_where;
        this.locale = locale;
        this.name = name;
    }

    @NonNull
    public String getUniqueId() {
        return uniqueId;
    }

    @NonNull
    public String getInfo() {
        return info;
    }

    @NonNull
    public String getInfo_of_where() {
        return info_of_where;
    }

    @NonNull
    public String getLocale() {
        return locale;
    }

    @NonNull
    public String getName() {
        return name;
    }
}

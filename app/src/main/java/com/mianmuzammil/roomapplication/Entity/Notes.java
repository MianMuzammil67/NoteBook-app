package com.mianmuzammil.roomapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_DATABASE")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "tittle")
    public String Tittle;
    @ColumnInfo(name = "subTittle")
    public String subTittle;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "notes")
    public String Notes;
    @ColumnInfo(name = "priority")
    public String priority;

}

package com.mianmuzammil.roomapplication.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mianmuzammil.roomapplication.DAO.DAO;
import com.mianmuzammil.roomapplication.Entity.Notes;

@Database(entities = {Notes.class} , version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract DAO NotesDao();

    public static NotesDatabase INSTANCE;
    public static NotesDatabase getINSTANCE(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"Notes_DATABASE").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }
}

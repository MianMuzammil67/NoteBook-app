package com.mianmuzammil.roomapplication.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mianmuzammil.roomapplication.Entity.Notes;

import java.util.List;

@Dao
public interface DAO {

    @Query("select * from Notes_DATABASE")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes_DATABASE ORDER BY priority ASC")
    LiveData<List<Notes>> lowerToHigh();

    @Query("SELECT * FROM Notes_DATABASE ORDER BY priority DESC")
    LiveData <List<Notes>> highToLow();

//    List<Notes> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_DATABASE WHERE id=:id")
    void DeleteNotes(int id);

    @Update
    void updateNotes(Notes notes);

}

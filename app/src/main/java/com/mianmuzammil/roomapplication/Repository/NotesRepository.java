package com.mianmuzammil.roomapplication.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mianmuzammil.roomapplication.DAO.DAO;
import com.mianmuzammil.roomapplication.DataBase.NotesDatabase;
import com.mianmuzammil.roomapplication.Entity.Notes;

import java.util.List;

public class NotesRepository {
    public DAO dao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowToHigh;
    public LiveData<List<Notes>> highToLow;

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getINSTANCE(application);
        dao = database.NotesDao();
        getAllNotes = dao.getAllNotes();
        lowToHigh = dao.lowerToHigh();
        highToLow = dao.highToLow();
    }

    public void InsertNotes(Notes notes) {
        dao.insertNotes(notes);
    }

    public void DeleteNotes(int id) {
        dao.DeleteNotes(id);
    }

    public void UpdateNotes(Notes notes) {
        dao.updateNotes(notes);
    }
}


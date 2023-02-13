package com.mianmuzammil.roomapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mianmuzammil.roomapplication.Entity.Notes;
import com.mianmuzammil.roomapplication.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
        lowToHigh = repository.lowToHigh;
        highToLow = repository.highToLow;
    }
    public void insertNote(Notes notes) {
        repository.InsertNotes(notes);
    }

    public void UpdateNOte(Notes notes) {
        repository.UpdateNotes(notes);
    }

    public void deleteNote(int id) {
        repository.DeleteNotes(id);
    }

}

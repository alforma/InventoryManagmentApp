package com.example.inventorymanagmentapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PartViewModel extends AndroidViewModel {
    private ProjectRepository repository;
    private LiveData<List<PartEntity>> allParts;

    public PartViewModel(@NonNull Application application) {
        super(application);
        repository = new ProjectRepository(application);
        allParts = repository.getAllParts();
    }

    public LiveData<List<PartEntity>> getAllParts() {
        return allParts;
    }

    public void insert(PartEntity partEntity) {
        repository.insert(partEntity);
    }

    public void update(PartEntity partEntity) {
        repository.update(partEntity);
    }

    public void delete(PartEntity partEntity) {
        repository.delete(partEntity);
    }

    public void deleteAllParts() {
        repository.deleteAllParts();
    }

    public int lastID(){
        return allParts.getValue().size();
    }


}


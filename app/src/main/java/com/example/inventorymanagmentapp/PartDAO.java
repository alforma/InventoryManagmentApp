package com.example.inventorymanagmentapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PartDAO {


    @Insert   (onConflict = OnConflictStrategy.REPLACE)
    void insert(PartEntity partEntity);

    @Update
    void update(PartEntity partEntity);

    @Delete
    void delete(PartEntity partEntity);

    @Query("DELETE FROM part_Entity")
    void deleteAllParts();

    @Query("SELECT * FROM part_entity")
    LiveData<List<PartEntity>> getAllParts();
}

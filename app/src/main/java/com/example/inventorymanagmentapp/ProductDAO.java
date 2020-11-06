package com.example.inventorymanagmentapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ProductDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductEntity productEntity);

    @Update
    void update(ProductEntity productEntity);

    @Delete
    void delete(ProductEntity productEntity);

    @Query("DELETE FROM product_entity")
    void deleteAllProducts();

    @Query("SELECT * FROM product_entity")
    LiveData<List<ProductEntity>> getAllProducts();
}

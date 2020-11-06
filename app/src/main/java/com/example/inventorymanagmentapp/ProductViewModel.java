package com.example.inventorymanagmentapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel  extends AndroidViewModel {
    private ProjectRepository repository;
    private LiveData<List<ProductEntity>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProjectRepository(application);
        allProducts = repository.getAllProducts();
    }



    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }

    public void insert(ProductEntity productEntity) {
        repository.insert(productEntity);
    }

    public void update(ProductEntity productEntity) {
        repository.update(productEntity);
    }

    public void delete(ProductEntity productEntity) {
        repository.delete(productEntity
        );
    }

    public void deleteAllProducts() {
        repository.deleteAllProducts();
    }

    public int lastID(){
        return allProducts.getValue().size();
    }


}


package com.example.inventorymanagmentapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ProductTable extends AppCompatActivity {
 ProductViewModel productViewModel;

    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_table
        );
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Product Table");

        


        RecyclerView recyclerView = findViewById(R.id.recyclerViewProduct);
        final ProductAdapter adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



      productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getAllProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ProductEntity> productEntities) {

                adapter.setProductEntities(productEntities);
            }
        });




        FloatingActionButton fab = findViewById(R.id.floatingActionButtonADDprod);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductTable.this, ProductAddActivity.class);
                intent.putExtra("productInventoryID", productViewModel.lastID()+1);
                startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
            }
        });




        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                productViewModel.delete(adapter.
                        getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ProductTable.this, "Product deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);







}




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//    public ProductEntity(int productIDInventory, String prodName, String prodInv, String prodPrice, String productID, String prodMax, String prodMin)
            ProductEntity productEntity = new ProductEntity(productViewModel.lastID()+1, data.getStringExtra("productName"),  data.getStringExtra("productInventory"),data.getStringExtra("productPrice"),data.getStringExtra("productID"),data.getStringExtra("productMax"),data.getStringExtra("productMin"));
            productViewModel.insert(productEntity);
        }
    }






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
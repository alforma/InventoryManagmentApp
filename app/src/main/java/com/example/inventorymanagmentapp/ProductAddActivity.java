package com.example.inventorymanagmentapp;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class ProductAddActivity extends AppCompatActivity  implements OnItemSelectedListener{


    private ProductViewModel productViewModel;



    private EditText mProductInventory;
    private EditText mProductPrice;
    private EditText mProdID;
    private EditText mProdMax;
    private EditText mProdMin;
      Spinner productNameSpinner ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Add/Edit Products");

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);



        mProductInventory = findViewById(R.id.editprodInv);
        mProductPrice = findViewById(R.id.editprodprice);
        mProdID = findViewById(R.id.editProdId);
        mProdMax = findViewById(R.id.editprodMax);
        mProdMin = findViewById(R.id.editProdMin);

        final Spinner productNameSpinner = findViewById(R.id.prodSpinner);
        ArrayAdapter<CharSequence> adapterProduct = ArrayAdapter.createFromResource(this,
                R.array.productSpinner, android.R.layout.simple_spinner_item);
        adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productNameSpinner.setAdapter(adapterProduct);
        productNameSpinner.setOnItemSelectedListener(this);

        if (getIntent().getStringExtra("productName") != null) {
            // editProdSpinner.setText(getIntent().getStringExtra("productName"));
            mProdID.setText(getIntent().getStringExtra("productID"));
            mProductPrice.setText(getIntent().getStringExtra("productPrice"));
            mProductInventory.setText(getIntent().getStringExtra("productInventory"));
            mProdMax.setText(getIntent().getStringExtra("productMax"));
            mProdMin.setText(getIntent().getStringExtra("productMin"));



         int spinnerValue = 0;

        String stat2 = getIntent().getStringExtra("productName");
        if (stat2.equals("productWidgetABC")) spinnerValue = 0;
        if (stat2.equals("productWidgetXYZ")) spinnerValue = 1;
        if (stat2.equals("productWidgetCBA")) spinnerValue = 2;
        if (stat2.equals("productWidgetZYX")) spinnerValue = 3;

            productNameSpinner .setSelection(spinnerValue);
    }











        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                String productID = mProdID.getText().toString();
                String productInv = mProductInventory.getText().toString();
                String productPrice = mProductPrice.getText().toString();
                String productMax = mProdMax.getText().toString();
                String productMin = mProdMin.getText().toString();
                String productName = productNameSpinner.getSelectedItem().toString();

                replyIntent.putExtra("productID", productID);
                replyIntent.putExtra("productName",productName);
                replyIntent.putExtra("productInventory",productInv);
                replyIntent.putExtra("productPrice", productPrice);
                replyIntent.putExtra("productMax", productMax);
                replyIntent.putExtra("productMin", productMin);


                if (getIntent().getStringExtra("productName") != null) {
                    int ID = getIntent().getIntExtra("productInventoryID", 0);
//    public ProductEntity(int productIDInventory, String prodName, String prodInv, String prodPrice, String productID, String prodMax, String prodMin)
                    ProductEntity productEntity = new ProductEntity(ID,  productName,productInv, productPrice,productID,productMax,productMin);
                  productViewModel.insert(productEntity);

                }
                setResult(RESULT_OK, replyIntent);
                finish();
            }

        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text2 = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class PartAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    RadioGroup radioGroup;

    EditText meditPartID;
    EditText meditPartInv;
    EditText meditPartMax;
    EditText meditPartMin;
    EditText meditPartPrice;
    EditText meditCompanyName;
    PartViewModel partViewModel;



    public static final int NEW_PART_ACTIVITY_REQUEST_CODE = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_add);


        partViewModel = new ViewModelProvider(this).get(PartViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Add/Edit Parts");


        meditCompanyName = findViewById(R.id.editTextCompany);
        meditPartID = findViewById(R.id.editTextPartId);
        meditPartInv = findViewById(R.id.editTextInv);
        meditPartMax = findViewById(R.id.editTextMax);
        meditPartMin = findViewById(R.id.editTextMin);
        meditPartPrice = findViewById(R.id.editTextPartPrice);



        final Spinner partNameSpinner = findViewById(R.id.partNameSpinner);
        ArrayAdapter<CharSequence> adapterPart = ArrayAdapter.createFromResource(this,
                R.array.partSpinner, android.R.layout.simple_spinner_item);
        adapterPart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partNameSpinner.setAdapter(adapterPart);
        partNameSpinner.setOnItemSelectedListener(this);


        if (getIntent().getStringExtra("partID") != null) {
            meditPartPrice.setText(getIntent().getStringExtra("partPrice"));
            meditPartID.setText(getIntent().getStringExtra("partID"));
            meditPartInv.setText(getIntent().getStringExtra("partInventory"));
            meditPartMax.setText(getIntent().getStringExtra("partMax"));
            meditPartMin.setText(getIntent().getStringExtra("partMin"));
            meditCompanyName.setText(getIntent().getStringExtra("partCompanyName"));

            int spinnerValue = 0;

            String stat = getIntent().getStringExtra("partName");
            if (stat.equals("partNameABC")) spinnerValue = 0;
            if (stat.equals("partNameXYZ")) spinnerValue = 1;
            if (stat.equals("partNameCBA")) spinnerValue = 2;
            if (stat.equals("partNameZYX")) spinnerValue = 3;

            partNameSpinner.setSelection(spinnerValue);
        }


        final Button savePartButton = findViewById(R.id.buttonPart);
        savePartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String partID = meditPartID.getText().toString();
                String partName = partNameSpinner.getSelectedItem().toString();

                String partInventory = meditPartInv.getText().toString();
                String partPrice = meditPartPrice.getText().toString();
                String partMax = meditPartMax.getText().toString();

                String partMin = meditPartMin.getText().toString();

                String companyName = meditCompanyName.getText().toString();

                replyIntent.putExtra("partID", partID);
                replyIntent.putExtra("partName", partName);
                replyIntent.putExtra("partInventory", partInventory);
                replyIntent.putExtra("partPrice", partPrice);
                replyIntent.putExtra("partMax", partMax);
                replyIntent.putExtra("partMin", partMin);
                replyIntent.putExtra("partCompanyName", companyName);


                if (getIntent().getStringExtra("partID") != null) {
                    int id = getIntent().getIntExtra("partInventoryID", 0);
                    PartEntity partEntity = new PartEntity(id, partID, partName, partInventory, partPrice, partMax, partMin, companyName);
                    partViewModel.insert(partEntity);
//    public PartEntity(int partInventoryID, String partID, String partName, String partInventory, String partPrice, String partMax, String partMin, String companyName)

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
        String text1 = parent.getItemAtPosition(position).toString();
    }





    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
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

import static android.widget.LinearLayout.HORIZONTAL;

public class PartTable extends AppCompatActivity {

    PartViewModel partViewModel;

    public static final int NEW_PART_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_table);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

      //  partViewModel = new ViewModelProvider(this).get(PartViewModel.class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Part Table");




        RecyclerView recyclerView = findViewById(R.id.recyclerViewPart);
        final PartAdapter madapter = new PartAdapter(this);
        recyclerView.setAdapter(madapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        partViewModel = new ViewModelProvider(this).get(PartViewModel.class);

        partViewModel.getAllParts().observe(this, new Observer<List<PartEntity>>() {
            @Override
            public void onChanged(@Nullable final List<PartEntity> partEntities) {

                madapter.setPartEntities(partEntities);
            }
        });



        FloatingActionButton fab = findViewById(R.id.floatingActionButtonADD);
        partViewModel = new ViewModelProvider(this).get(PartViewModel.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PartTable.this, PartAddActivity.class);
                intent.putExtra("partInventoryID", partViewModel.lastID()+1);
                startActivityForResult(intent, NEW_PART_ACTIVITY_REQUEST_CODE);
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
                partViewModel.delete(madapter.getPartAt(viewHolder.getAdapterPosition()));
                Toast.makeText(PartTable.this, "Thing deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);











    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            PartEntity partEntity = new PartEntity(partViewModel.lastID()+1,  data.getStringExtra("partID"),data.getStringExtra("partName"), data.getStringExtra("partInventory"),data.getStringExtra("partPrice"),data.getStringExtra("partMax"),data.getStringExtra("partMin"), data.getStringExtra("partCompanyName"));
            partViewModel.insert(partEntity);
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
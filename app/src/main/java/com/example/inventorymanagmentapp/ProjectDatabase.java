package com.example.inventorymanagmentapp;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {ProductEntity.class, PartEntity.class},exportSchema = false, version = 5)

public  abstract class ProjectDatabase extends RoomDatabase {
    public abstract ProductDAO productDAO();
    public abstract PartDAO partDAO();


    private static volatile ProjectDatabase INSTANCE;

    static ProjectDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjectDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProjectDatabase.class, "project_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);


        }
    };

}



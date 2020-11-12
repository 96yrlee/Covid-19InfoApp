package com.example.covid19appretrotest.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {Zone.class, Countries.class},
        version = 1
)
public abstract class CovidDatabase extends RoomDatabase {

    private static CovidDatabase instance;

    //to handle async
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // to access global dao + its methods
    public abstract ZoneDao mZoneDao();
    public abstract CountriesDao mCountryDao();

    public static synchronized CovidDatabase getInstance(Context context){
        Log.d("Cov Database", "getInstance called");
        // we only want to create if we don't already have this table open
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                            CovidDatabase.class, "covid_database")
                    .fallbackToDestructiveMigration() //if we increment ver# it will delete and recreate
                    //.addCallback(sRoomDatabaseCallback) // dummy insert
                    .build();
        }
        return instance; //returns the existing if not null
    }


/*
    /**
     * TEST POPULATE DATABASE
     * to see if the item card shows up and how it shows up with viewmodel
     * fill database with dummy data
     *
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {

                Log.d("Populate Database", "executor has been called here");
                ZoneDao dao = instance.mZoneDao();

                Zone dummyZoneEntity = new Zone("Nov 1st 2020", "Global", 10000000L,
                        7342359, 414124, 3619774, 3308461,
                        31527, 21016, 1152,
                        1591782903866L, 7753933875L, 102586329L);

                dao.insert(dummyZoneEntity);
            });
        }
    };
    */
}

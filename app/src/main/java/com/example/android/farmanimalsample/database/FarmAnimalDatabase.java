package com.example.android.farmanimalsample.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Animal.class}, version = 1, exportSchema = false)
public abstract class FarmAnimalDatabase extends RoomDatabase {

    private static volatile FarmAnimalDatabase INSTANCE;

    public abstract AnimalDao animalDao();

    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Method will create database object for the first time its accessed or return the existing database object.
     *
     * @param context
     * @return singleton farm_animal database object
     */
    public static FarmAnimalDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (FarmAnimalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FarmAnimalDatabase.class, "animal_database")
                            .addCallback(farmAnimalDBCallbacks).build();
                }
            }
        }
        return INSTANCE;
    }

    //Override onCreate method of Roomdatabase to del all previous data in DB while creating DB and insert few farm animals after creating farm animal
    //database
    private static RoomDatabase.Callback farmAnimalDBCallbacks = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbWriteExecutor.execute(()->{
                AnimalDao dao = INSTANCE.animalDao();
                dao.deleteAll();
                Animal a = new Animal("Cow");
                dao.insert(a);
                a = new Animal("Horse");
                dao.insert(a);
            });

        }

    };

}

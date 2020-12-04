package com.example.android.farmanimalsample.Utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.farmanimalsample.database.Animal;
import com.example.android.farmanimalsample.database.AnimalDao;
import com.example.android.farmanimalsample.database.FarmAnimalDatabase;

import java.util.List;

/***
 * AnimalRepository class abstract data sources of application from rest of the app and provides the
 * APIs to access local or cloud DB.
 */
public class AnimalRepository {

    private AnimalDao animalDao;
    private LiveData<List<Animal>> mAllFarmAnimal;

    public AnimalRepository(Application appliation){
        FarmAnimalDatabase animalDB = FarmAnimalDatabase.getInstance(appliation);
        this.animalDao = animalDB.animalDao();
        mAllFarmAnimal = animalDao.getAnimals();
    }

    public LiveData<List<Animal>> getAllFarmAnimal(){
        return mAllFarmAnimal;
    }

    public void insetAnimal(Animal animal){
        FarmAnimalDatabase.dbWriteExecutor.execute(()->{
            animalDao.insert(animal);
        });

    }
}

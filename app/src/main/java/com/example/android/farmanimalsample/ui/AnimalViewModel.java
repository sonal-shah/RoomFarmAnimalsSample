package com.example.android.farmanimalsample.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.farmanimalsample.Utils.AnimalRepository;
import com.example.android.farmanimalsample.database.Animal;

import java.util.List;

/**
 * AnimalViewModel class takes the data from repository and giving it to UI. This ViewModel class
 * donot make direct call to the database but through repository.
 */
public class AnimalViewModel extends AndroidViewModel {
    private AnimalRepository mRepository ;
    private LiveData<List<Animal>> mAllAnimals;

    public AnimalViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AnimalRepository(application);
        mAllAnimals = mRepository.getAllFarmAnimal();
    }

    public LiveData<List<Animal>> getAllAnimals(){
        return mAllAnimals;
    }

    public void insertAnimal(Animal animal){
        mRepository.insetAnimal(animal);
    }
}

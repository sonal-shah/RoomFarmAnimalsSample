package com.example.android.farmanimalsample.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/***
 * Provides operation likes
 *  - Get all animals in ascending order
 *  - Delete all animals
 *  - Insert a farm animal
 */

@Dao
public interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Animal animal);

    @Query("DELETE FROM animal_table")
    void deleteAll();

    @Query("SELECT * FROM animal_table ORDER BY name ASC")
    LiveData<List<Animal>>getAnimals();

}

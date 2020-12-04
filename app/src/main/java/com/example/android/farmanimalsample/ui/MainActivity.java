package com.example.android.farmanimalsample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.farmanimalsample.R;
import com.example.android.farmanimalsample.database.Animal;
import com.example.android.farmanimalsample.ui.AnimalListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimalViewModel mViewModel;
    public static final int NEW_ANIMAL_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.animals_recycleview);
        AnimalListAdapter adapter = new AnimalListAdapter(new AnimalListAdapter.AnimalDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //ViewModelProvider creates AnimalViewModel or returns the existing one.
        mViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AnimalViewModel.class);

        //Activity is becoming observer of live data i.e. list of animals
        mViewModel.getAllAnimals().observe(this,animals -> {
            //adapter cached copy is updated when live data updated in database
            adapter.submitList(animals);
        });

        FloatingActionButton fab = findViewById(R.id.addAnimal_button);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,NewAnimalActivity.class);
            startActivityForResult(intent,NEW_ANIMAL_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ANIMAL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Animal animal = new Animal(data.getStringExtra(NewAnimalActivity.EXTRA_REPLY));
            mViewModel.insertAnimal(animal);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
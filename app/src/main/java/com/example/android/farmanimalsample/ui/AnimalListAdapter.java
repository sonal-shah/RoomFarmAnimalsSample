package com.example.android.farmanimalsample.ui;

import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.android.farmanimalsample.database.Animal;

public class AnimalListAdapter extends ListAdapter<Animal,AnimalViewHolder> {

    public AnimalListAdapter(@NonNull DiffUtil.ItemCallback<Animal> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AnimalViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal current = getItem(position);
        holder.bind(current.getName());
    }

   public static class AnimalDiff extends DiffUtil.ItemCallback<Animal> {

        @Override
        public boolean areItemsTheSame(@NonNull Animal oldItem, @NonNull Animal newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Animal oldItem, @NonNull Animal newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}

package com.example.android.farmanimalsample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.farmanimalsample.R;

public class AnimalViewHolder extends RecyclerView.ViewHolder {

    private final TextView animalItemView;

    public AnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        animalItemView = itemView.findViewById(R.id.name_text);

    }

    public void bind(String name){
        animalItemView.setText(name);
    }

    static AnimalViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_listitem, parent, false);
        return new AnimalViewHolder(view);
    }

}

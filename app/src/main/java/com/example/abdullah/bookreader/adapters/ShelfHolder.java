package com.example.abdullah.bookreader.adapters;

import android.view.View;

import com.example.abdullah.bookreader.data.models.ShelfModel;
import com.example.abdullah.bookreader.databinding.CardShelfBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShelfHolder extends RecyclerView.ViewHolder {
    CardShelfBinding binding;
    public ShelfHolder(@NonNull CardShelfBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    protected void bind(ShelfModel model){
        binding.setShelf(model);
    }
}

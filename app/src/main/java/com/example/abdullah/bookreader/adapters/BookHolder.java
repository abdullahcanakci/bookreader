package com.example.abdullah.bookreader.adapters;

import android.view.View;
import android.widget.ImageView;

import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.databinding.CardBookBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class BookHolder extends RecyclerView.ViewHolder {
    private CardBookBinding binding;
    BookHolder(@NonNull CardBookBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    void bind(BookModel model){
        binding.setBook(model);
    }
    protected ImageView getCoverView() {return binding.cover; }
}

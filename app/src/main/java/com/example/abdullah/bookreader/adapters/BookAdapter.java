package com.example.abdullah.bookreader.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abdullah.bookreader.R;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.databinding.CardBookBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
    List<BookModel> books = new ArrayList<>();
    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_book, parent, false);
        return new BookHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.bind(books.get(position));
    }

    public void updateList(List<BookModel> books){
        this.books = books;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}

package com.example.abdullah.bookreader.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abdullah.bookreader.R;
import com.example.abdullah.bookreader.data.models.ShelfModel;
import com.example.abdullah.bookreader.databinding.CardShelfBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ShelfAdapter extends RecyclerView.Adapter<ShelfHolder> {
    List<ShelfModel> shelfs = new ArrayList<>();
    @NonNull
    @Override
    public ShelfHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardShelfBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_shelf, parent, false);
        return new ShelfHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelfHolder holder, int position) {
        holder.bind(shelfs.get(position));
    }

    public void updateList(List<ShelfModel> models){
        this.shelfs = models;
    }

    @Override
    public int getItemCount() {
        return shelfs.size();
    }
}

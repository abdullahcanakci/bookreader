package com.example.abdullah.bookreader.adapters;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.bumptech.glide.Glide;
import com.example.abdullah.bookreader.InjectorUtils;
import com.example.abdullah.bookreader.R;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.databinding.CardBookBinding;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;
import com.example.abdullah.bookreader.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
    List<BookModel> books = new ArrayList<>();
    MenuSelectionListener listener;

    public BookAdapter() {
        listener = InjectorUtils.provideMenuSelectionListener();
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_book, parent, false);
        binding.menu.setOnClickListener((view) ->{
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.menu_book, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener((menuItem)->{
                if(listener != null)
                switch (menuItem.getItemId()){
                    case R.id.open:
                        listener.onBookOpened(binding.getBook().getId());
                        break;
                    case R.id.edit:
                        listener.onBookEdited(binding.getBook().getId());
                        break;
                }
                return true;
            });
        });
        return new BookHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.bind(books.get(position));
        ImageView i = holder.getCoverView();
        GlideApp.with(i).clear(i);
        GlideApp
                .with(i)
                .load(R.drawable.cover)
                .placeholder(R.drawable.placeholder_cover)
                .into(i);
    }

    public void updateList(List<BookModel> books){
        this.books = books;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setListener(MenuSelectionListener listener) {
        this.listener = listener;
    }
}

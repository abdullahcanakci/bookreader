package com.example.abdullah.bookreader.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abdullah.bookreader.databinding.FileListItemBinding;
import com.example.abdullah.bookreader.data.models.FileModel;
import com.example.abdullah.bookreader.fragments.FileExplorerFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExplorerAdapter extends RecyclerView.Adapter<FileHolder> {
    private static int FILE = 100;
    private static int FOLDER = 101;

    private List<FileModel> mFileModels = new ArrayList<>();
    private FileExplorerFragment parent;

    public ExplorerAdapter(FileExplorerFragment fragment) {
        this.parent = fragment;
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FileListItemBinding binding = FileListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        binding.getRoot().setOnClickListener((view) -> this.parent.onClickView(binding.getFile()));


        return new FileHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull FileHolder holder, int position) {
        holder.bind(mFileModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mFileModels.size();
    }

    public void updateList(List<FileModel> files) {
        this.mFileModels = files;
        this.notifyDataSetChanged();
    }

}

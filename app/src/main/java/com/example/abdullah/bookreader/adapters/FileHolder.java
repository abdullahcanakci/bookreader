package com.example.abdullah.bookreader.adapters;

import com.example.abdullah.bookreader.databinding.FileListItemBinding;
import com.example.abdullah.bookreader.data.models.FileModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FileHolder extends RecyclerView.ViewHolder {
    private FileListItemBinding binding;
    FileHolder(@NonNull FileListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(FileModel fileModel) {
        binding.setFile(fileModel);
    }
}

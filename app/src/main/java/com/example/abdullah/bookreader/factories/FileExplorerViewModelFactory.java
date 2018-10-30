package com.example.abdullah.bookreader.factories;

import android.content.Context;

import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.viewmodels.FileExplorerViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FileExplorerViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private Repository repo;

    public FileExplorerViewModelFactory(Context context, Repository repo) {
        this.context = context;
        this.repo = repo;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FileExplorerViewModel(context, repo);
    }
}

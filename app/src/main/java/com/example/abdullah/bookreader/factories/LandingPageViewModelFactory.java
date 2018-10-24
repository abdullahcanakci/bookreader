package com.example.abdullah.bookreader.factories;

import android.content.Context;

import com.example.abdullah.bookreader.data.AppRepository;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.viewmodels.LandingPageViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LandingPageViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context mContext;
    private final Repository mRepository;

    public LandingPageViewModelFactory(Context context, Repository repository) {
        mContext = context;
        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LandingPageViewModel(mContext, mRepository);
    }
}

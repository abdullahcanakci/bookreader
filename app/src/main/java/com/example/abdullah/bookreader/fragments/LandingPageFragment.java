package com.example.abdullah.bookreader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdullah.bookreader.InjectorUtils;
import com.example.abdullah.bookreader.databinding.LandingPageFragmentBinding;
import com.example.abdullah.bookreader.factories.LandingPageViewModelFactory;
import com.example.abdullah.bookreader.helpers.FragmentType;
import com.example.abdullah.bookreader.helpers.FragmentTypeInterface;
import com.example.abdullah.bookreader.listeners.NavigationChild;
import com.example.abdullah.bookreader.listeners.NavigationListener;
import com.example.abdullah.bookreader.viewmodels.LandingPageViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class LandingPageFragment extends Fragment implements NavigationChild, FragmentTypeInterface {

    private LandingPageViewModel mViewModel;
    private LandingPageFragmentBinding binding;

    public static LandingPageFragment getInstance() {
        return new LandingPageFragment();
    }
    //private LandingPageFragmentBinding mBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LandingPageViewModelFactory factory = InjectorUtils.provideLandingPageViewModeFactory(getContext());
        mViewModel = ViewModelProviders
                .of(this, factory)
                .get(LandingPageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LandingPageFragmentBinding.inflate(getLayoutInflater(), container, false);
        binding.setBinding(mViewModel);
        binding.buttonAdd.setOnClickListener((view)-> {
            ((NavigationListener) InjectorUtils.provideNavigationHandler()).goTo(FragmentType.EXPLORER_FRAGMENT);
        });
        return binding.getRoot();
    }

    @Override
    public boolean goBack() {
        return false;
    }

    @Override
    public FragmentType getType() {
        return FragmentType.LANDING_FRAGMENT;
    }
}

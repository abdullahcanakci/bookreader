package com.example.abdullah.bookreader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.abdullah.bookreader.InjectorUtils;
import com.example.abdullah.bookreader.R;
import com.example.abdullah.bookreader.adapters.ExplorerAdapter;
import com.example.abdullah.bookreader.data.models.FileModel;
import com.example.abdullah.bookreader.factories.FileExplorerViewModelFactory;
import com.example.abdullah.bookreader.helpers.FragmentType;
import com.example.abdullah.bookreader.helpers.FragmentTypeInterface;
import com.example.abdullah.bookreader.listeners.NavigationChild;
import com.example.abdullah.bookreader.viewmodels.FileExplorerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This fragment is responsible for displaying any info produced by {@link FileExplorerViewModel}.
 */
public class FileExplorerFragment extends Fragment implements NavigationChild, FragmentTypeInterface {

    private FileExplorerViewModel viewModel;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    public static FileExplorerFragment getInstance() {
        return new FileExplorerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileExplorerViewModelFactory factory = InjectorUtils.provideFileExplorerViewModelFactory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(FileExplorerViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.file_explorer_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        ExplorerAdapter adapter = new ExplorerAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        //PERFORMANCE ENCHANTMENTS
        //60 frame to 40
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        mRecyclerView.getContext(),
                        RecyclerView.VERTICAL)
        );
        mRecyclerView.setAdapter(adapter);
        viewModel.getFileList().observe(this, (files) -> {
            adapter.updateList(files);
            toggleLoading(false);
        });
        FloatingActionButton button = view.findViewById(R.id.fab_confirm);

        //Confirm selection
        button.setOnClickListener((v) -> {
            viewModel.pushSelectedItemsToRepository();
        });
        mProgressBar = view.findViewById(R.id.progress_bar);
        viewModel.goToDir(viewModel.mStartPath);
        return view;
    }

    public void onClickView(FileModel model) {
        if (!model.isFile()) {
            toggleLoading(true);
            viewModel.goToDir(model.getPath());
        }
    }

    public boolean goBack() {
        return viewModel.goBack();
    }

    private void toggleLoading(boolean loading) {
        if (loading) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public FragmentType getType() {
        return FragmentType.EXPLORER_FRAGMENT;
    }
}

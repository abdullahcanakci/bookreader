package com.example.abdullah.bookreader.viewmodels;

import android.content.Context;

import com.example.abdullah.bookreader.R;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.ShelfModel;

import androidx.lifecycle.ViewModel;

public class LandingPageViewModel extends ViewModel {
    private final Context mContext;
    private final Repository mRepository;
    private ShelfModel mAddedLast = new ShelfModel();
    private ShelfModel mInteractedLast = new ShelfModel();
    private BookModel mReadLast = new BookModel();

    public LandingPageViewModel(Context context, Repository repository) {
        mContext = context;
        mRepository = repository;
        String add = context.getResources().getString(R.string.last_added_books);
        String read = context.getResources().getString(R.string.last_interacted_books);
        mAddedLast.setName(add);
        mInteractedLast.setName(read);

        repository.getAddedLast().observeForever((books) -> {
            mAddedLast.setBooks(books);
        });

        repository.getInteractedLast().observeForever((books)->{
            mInteractedLast.setBooks(books);
            mReadLast = books.get(0);
        });
    }

    public ShelfModel getAddedLast() {
        return mAddedLast;
    }

    public ShelfModel getInteractedLast() {
        return mInteractedLast;
    }

    public BookModel getReadLast() {
        return mReadLast;
    }
}

package com.example.abdullah.bookreader.data;

import android.util.Log;

import com.example.abdullah.bookreader.AppExecutors;
import com.example.abdullah.bookreader.data.database.AppDatabase;
import com.example.abdullah.bookreader.data.database.BookDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class AppRepository implements Repository{
    private static final String TAG = "AppRepository";

    private static final Object LOCK = new Object();
    private static AppRepository sInstance;
    private static BookDao mBookDao;

    private AppExecutors mExecutors;

    /**
     * Creates repository with provided
     */
    private AppRepository(AppDatabase database, AppExecutors executors){
        //Fill the DAO's
        this.mExecutors = executors;
        mBookDao = database.getBookDao();
    }

    /**
     *
     * If the database instance is provided null this method will create a dummy repository that serves test data
     * @param database AppDatabase instance
     * @param executors AppExecutors instance
     */
    public synchronized static AppRepository getInstance(@NonNull AppDatabase database, @NonNull AppExecutors executors){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new AppRepository(database, executors);
                Log.d(TAG, "Created new repository");
            }
        }
        return sInstance;
    }

    @Override
    public void addBook(BookModel bookModel) {
        mBookDao.insert(bookModel);
    }

    @Override
    public void addBooks(List<BookModel> bookModels) {
        mBookDao.insert((BookModel[]) (bookModels.toArray()));
    }

    @Override
    public LiveData<List<BookModel>> getAllBooks() {
        return mBookDao.getAllBooks();
    }

    @Override
    public LiveData<List<BookModel>> getAllBooksById(List<Long> ids) {
        return mBookDao.getBooksByIds(ids);
    }

    @Override
    public LiveData<BookModel> getBookById(Long id) {
        return mBookDao.getBookById(id);
    }

    @Override
    public void updateBook(BookModel bookModel) {
        mBookDao.update(bookModel);
    }

    @Override
    public void updateBooks(List<BookModel> bookModels) {
        mBookDao.update((BookModel[]) bookModels.toArray());
    }

    @Override
    public void deleteBook(BookModel bookModel) {
        mBookDao.delete(bookModel);
    }

    @Override
    public void deleteBookById(long id) {
    }
}

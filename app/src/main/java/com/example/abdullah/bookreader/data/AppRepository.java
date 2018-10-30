package com.example.abdullah.bookreader.data;

import android.util.Log;

import com.example.abdullah.bookreader.AppExecutors;
import com.example.abdullah.bookreader.data.database.AppDatabase;
import com.example.abdullah.bookreader.data.database.BookDao;
import com.example.abdullah.bookreader.data.database.FileModelDao;
import com.example.abdullah.bookreader.data.database.ShelfBookJoinDao;
import com.example.abdullah.bookreader.data.database.ShelfDao;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.FileModel;
import com.example.abdullah.bookreader.data.models.ShelfBookJoinModel;
import com.example.abdullah.bookreader.data.models.ShelfModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Transaction;

public class AppRepository implements Repository{
    private static final String TAG = "AppRepository";

    private static final Object LOCK = new Object();
    private static AppRepository sInstance;
    private static BookDao sBookDao;
    private static ShelfDao sShelfDao;
    private static ShelfBookJoinDao sShelfBookJoinModel;
    private final FileModelDao fileModelDao;

    private AppExecutors mExecutors;

    /**
     * Creates repository with provided
     */
    private AppRepository(AppDatabase database, AppExecutors executors){
        //Fill the DAO's
        this.mExecutors = executors;
        sBookDao = database.getBookDao();
        sShelfDao = database.getShelfDao();
        sShelfBookJoinModel = database.getShelfBookJoinDao();
        this.fileModelDao = database.getFileModelDao();
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
        sBookDao.insert(bookModel);
    }

    @Override
    public void addBooks(List<BookModel> bookModels) {
        sBookDao.insert(bookModels);
    }

    @Override
    public LiveData<List<BookModel>> getAllBooks() {
        return sBookDao.getAllBooks();
    }

    @Override
    public LiveData<List<BookModel>> getAllBooksById(List<Long> ids) {
        return sBookDao.getBooksByIds(ids);
    }

    @Override
    public LiveData<BookModel> getBookById(Long id) {
        return sBookDao.getBookById(id);
    }

    @Override
    public void updateBook(BookModel bookModel) {
        sBookDao.update(bookModel);
    }

    @Override
    public void updateBooks(List<BookModel> bookModels) {
        sBookDao.update(bookModels);
    }

    @Override
    public void deleteBook(BookModel bookModel) {
        sBookDao.delete(bookModel);
    }

    @Override
    public void deleteBookById(long id) {
    }

    @Override
    public LiveData<List<ShelfModel>> getShelvesAndBooks() {
        MutableLiveData<List<ShelfModel>> models = new MutableLiveData<>();
        mExecutors.diskIO().execute(() -> {
            List<ShelfModel> shelfModels = sShelfDao.getAllShelvesSync();
            for (ShelfModel m : shelfModels) {
                m.setBooks(sShelfBookJoinModel.getBooksForShelfSync(m.getId()));
            }
            models.postValue(shelfModels);
        });
        return models;
    }

    @Override
    public LiveData<List<ShelfModel>> getShelvesForDisplay() {
        MutableLiveData<List<ShelfModel>> models = new MutableLiveData<>();
        mExecutors.diskIO().execute(() -> {
            List<ShelfModel> shelfModels = sShelfDao.getAllShelvesSync();
            for (ShelfModel m : shelfModels) {
                m.setBooks(sShelfBookJoinModel.getBooksForShelf(m.getId(), 5));
            }
            models.postValue(shelfModels);
        });
        return models;
    }

    @Override
    public LiveData<List<BookModel>> getBooksForShelf(long id) {
        return sShelfBookJoinModel.getBooksForShelf(id);
    }

    @Override
    @Transaction
    public void addBooksToShelf(List<BookModel> books, ShelfModel shelf) {
        mExecutors.diskIO().execute(()->{
            for(BookModel model: books){
                ShelfBookJoinModel m = new ShelfBookJoinModel(shelf.getId(), model.getId());
                sShelfBookJoinModel.insert(m);
                shelf.setCount(shelf.getCount() + books.size());
                sShelfDao.update(shelf);
            }
        });
    }

    @Override
    public void addBookToShelf(BookModel book, ShelfModel shelf) {
        mExecutors.diskIO().execute(()-> {
            ShelfBookJoinModel m = new ShelfBookJoinModel(shelf.getId(), book.getId());
            sShelfBookJoinModel.insert(m);
            shelf.setCount(shelf.getCount() + 1);
            sShelfDao.update(shelf);
        });
    }

    @Override
    public LiveData<List<BookModel>> getAddedLast() {
        return sBookDao.getAddedLast();
    }

    @Override
    public LiveData<List<BookModel>> getInteractedLast() {
        return sBookDao.getInteractedLast();
    }

    @Override
    public LiveData<List<FileModel>> getFileModels() {
        return fileModelDao.getFileModels();
    }

    @Override
    public void deleteFileModels(List<FileModel> fileModelList) {
        mExecutors.diskIO().execute(() -> {
            fileModelDao.delete(fileModelList);
        });
    }

    @Override
    public void insertFileModels(List<FileModel> fileModelList) {
        mExecutors.diskIO().execute(() -> {
            fileModelDao.insert(fileModelList);
        });
    }
}

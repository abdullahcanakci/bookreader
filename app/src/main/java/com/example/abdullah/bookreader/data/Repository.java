package com.example.abdullah.bookreader.data;

import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.ShelfModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {
    //CREATE
    void addBook(BookModel bookModel);
    void addBooks(List<BookModel> bookModels);

    //READ
    LiveData<List<BookModel>> getAllBooks();
    LiveData<List<BookModel>> getAllBooksById(List<Long> id);
    LiveData<BookModel> getBookById(Long id);

    //UPDATE
    void updateBook(BookModel bookModel);
    void updateBooks(List<BookModel> bookModels);

    //DELETE
    void deleteBook(BookModel bookModel);
    void deleteBookById(long id);

    LiveData<List<ShelfModel>> getShelves();
}

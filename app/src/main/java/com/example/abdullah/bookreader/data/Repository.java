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

    /**
     * Returns all shelves and books for them only used by debug and development purposes
     */
    LiveData<List<ShelfModel>> getShelvesAndBooks();

    /**
     * Returns all shelves and 5 books for each shelf to display on the Shelf recycler
     */
    LiveData<List<ShelfModel>> getShelvesForDisplay();

    /**
     * Returns books for provided shelf id
     */
    LiveData<List<BookModel>> getBooksForShelf(long id);

    /**
     * Insert into a shelf
     */
    void addBooksToShelf(List<BookModel> books, ShelfModel shelf);
    void addBookToShelf(BookModel book, ShelfModel shelf);

    LiveData<List<BookModel>> getAddedLast();
    LiveData<List<BookModel>> getInteractedLast();
}

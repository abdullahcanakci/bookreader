package com.example.abdullah.bookreader.data.database;

import com.example.abdullah.bookreader.data.models.BookModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookDao {
    @Insert
    void insert(BookModel books);
    @Insert
    void insert(List<BookModel> books);

    @Update
    void update(BookModel books);
    @Update
    void update(List<BookModel> books);

    @Delete
    void delete(BookModel books);
    @Delete
    void delete(List<BookModel> books);

    /**
     * Gets everything in the database. Should only to be used as a debugging tool.
     */
    @Query("SELECT * FROM books")
    LiveData<List<BookModel>> getAllBooks();

    @Query("SELECT * FROM books WHERE id = :id")
    LiveData<BookModel> getBookById(long id);

    @Query("SELECT * FROM books WHERE id IN (:ids)")
    LiveData<List<BookModel>> getBooksByIds(List<Long> ids);
}

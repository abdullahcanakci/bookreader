package com.example.abdullah.bookreader.data.database;

import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.ShelfBookJoinModel;
import com.example.abdullah.bookreader.data.models.ShelfModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public interface ShelfBookJoinDao {
    @Insert
    void insert(ShelfBookJoinModel shelfBookJoin);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM books INNER JOIN shelf_book_join ON " +
            "books.id=shelf_book_join.bookId WHERE " +
            "shelf_book_join.shelfId=:shelfId")
    LiveData<List<BookModel>> getBooksForShelf(final long shelfId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM books INNER JOIN shelf_book_join ON " +
            "books.id=shelf_book_join.bookId WHERE " +
            "shelf_book_join.shelfId=:shelfId")
    List<BookModel> getBooksForShelfSync(final long shelfId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM books INNER JOIN shelf_book_join ON " +
            "books.id=shelf_book_join.bookId WHERE " +
            "shelf_book_join.shelfId=:shelfId LIMIT :limit")
    List<BookModel> getBooksForShelf(final long shelfId, int limit);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM shelfs INNER JOIN shelf_book_join ON " +
            "shelfs.id=shelf_book_join.shelfId WHERE " +
            "shelf_book_join.bookId=:bookId")
    LiveData<List<ShelfModel>> getShelvesForBook(final long bookId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM shelfs INNER JOIN shelf_book_join ON " +
            "shelfs.id=shelf_book_join.shelfId WHERE " +
            "shelf_book_join.bookId=:bookId LIMIT :limit")
    LiveData<List<ShelfModel>> getShelvesForBook(final long bookId, int limit);
}

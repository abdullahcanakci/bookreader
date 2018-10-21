package com.example.abdullah.bookreader.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "shelf_book_join",
        primaryKeys = {"shelfId", "bookId"},
        indices = {@Index("shelfId"), @Index("bookId")},
        foreignKeys = {
                @ForeignKey(
                        entity = BookModel.class,
                        parentColumns = "id",
                        childColumns = "bookId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = ShelfModel.class,
                        parentColumns = "id",
                        childColumns = "shelfId",
                        onDelete = CASCADE
                )
        }

)
public class ShelfBookJoinModel {
    @ColumnInfo(name = "shelfId")
    private long mShelfId;
    @ColumnInfo(name = "bookId")
    private long mBookId;

    public ShelfBookJoinModel(long shelfId, long bookId) {
        mShelfId = shelfId;
        mBookId = bookId;
    }

    public long getShelfId() {
        return mShelfId;
    }

    public void setShelfId(long shelfId) {
        mShelfId = shelfId;
    }

    public long getBookId() {
        return mBookId;
    }

    public void setBookId(long bookId) {
        mBookId = bookId;
    }
}

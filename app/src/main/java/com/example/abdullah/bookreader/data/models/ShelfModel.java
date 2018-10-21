package com.example.abdullah.bookreader.data.models;

import com.example.abdullah.bookreader.adapters.BookAdapter;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "shelfs")
public class ShelfModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "title")
    private String mName;

    @Ignore
    List<BookModel> books;

    @Ignore
    BookAdapter adapter = new BookAdapter();

    @Ignore
    public ShelfModel() {
    }

    public ShelfModel(long id, String name) {
        mId = id;
        mName = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public BookAdapter getBooksAdapter(){
        return adapter;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
        adapter.updateList(books);
    }
}

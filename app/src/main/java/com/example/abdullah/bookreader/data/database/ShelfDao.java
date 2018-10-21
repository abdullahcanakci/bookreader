package com.example.abdullah.bookreader.data.database;

import com.example.abdullah.bookreader.data.models.ShelfModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ShelfDao {
    @Insert
    long insert(ShelfModel model);

    @Insert
    void insert(List<ShelfModel> models);

    @Query("SELECT * FROM shelfs")
    LiveData<List<ShelfModel>> getAllShelves();

    @Query("SELECT * FROM shelfs")
    List<ShelfModel> getAllShelvesA();
}

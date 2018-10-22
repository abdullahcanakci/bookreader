package com.example.abdullah.bookreader.data.database;

import com.example.abdullah.bookreader.data.models.ShelfModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ShelfDao {
    @Insert
    long insert(ShelfModel model);

    @Insert
    void insert(List<ShelfModel> models);

    @Update
    void update(ShelfModel model);

    @Query("SELECT * FROM shelfs")
    LiveData<List<ShelfModel>> getAllShelves();

    @Query("SELECT * FROM shelfs")
    List<ShelfModel> getAllShelvesSync();

    @Query("SELECT * FROM shelfs WHERE id = :id")
    LiveData<ShelfModel> getShelveById(long id);
}

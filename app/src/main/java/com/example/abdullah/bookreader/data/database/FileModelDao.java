package com.example.abdullah.bookreader.data.database;

import com.example.abdullah.bookreader.data.models.FileModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FileModelDao {
    @Insert
    void insert(List<FileModel> fileModelList);

    @Delete
    void delete(List<FileModel> fileModelList);

    @Query("SELECT * FROM fileModels")
    LiveData<List<FileModel>> getFileModels();
}

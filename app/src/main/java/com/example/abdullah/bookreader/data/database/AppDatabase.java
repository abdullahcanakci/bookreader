package com.example.abdullah.bookreader.data.database;

import android.content.Context;

import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.FileModel;
import com.example.abdullah.bookreader.data.models.ShelfBookJoinModel;
import com.example.abdullah.bookreader.data.models.ShelfModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BookModel.class, ShelfModel.class, ShelfBookJoinModel.class, FileModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "bookreader";
    private static final Object LOCK = new Object();
    private static volatile AppDatabase sInstance;

    //DAO'S
    public abstract BookDao getBookDao();
    public abstract ShelfDao getShelfDao();
    public abstract ShelfBookJoinDao getShelfBookJoinDao();
    public abstract FileModelDao getFileModelDao();


    public static AppDatabase getInstance(Context context, boolean regularDatabase) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {

                    if (!regularDatabase) {
                        sInstance = Room.inMemoryDatabaseBuilder(
                                context.getApplicationContext(),
                                AppDatabase.class
                        ).build();
                    } else {
                        sInstance = Room.databaseBuilder(
                                context.getApplicationContext(),
                                AppDatabase.class,
                                DATABASE_NAME
                        ).build();
                    }
                }
            }
        }
        return sInstance;
    }
}

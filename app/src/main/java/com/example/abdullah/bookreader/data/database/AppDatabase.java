package com.example.abdullah.bookreader.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "bookreader";
    private static final Object LOCK = new Object();
    private static volatile AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if(sInstance == null){
                    sInstance =
                            Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME
                            ).build();
                }
            }
        }
        return sInstance;
    }
}

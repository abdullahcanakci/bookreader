package com.example.abdullah.bookreader.data;

import android.util.Log;

import com.example.abdullah.bookreader.AppExecutors;
import com.example.abdullah.bookreader.data.database.AppDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

public class AppRepository {
    private static final String TAG = "AppRepository";

    private static final Object LOCK = new Object();
    private static AppRepository sInstance;

    private boolean isDummy = false;
    private AppExecutors mExecutors;

    /**
     * Creates a dummy repository
     */
    private AppRepository(){
        isDummy = true;
    }

    /**
     * Creates repository with provided
     */
    private AppRepository(AppDatabase database, AppExecutors executors){
        //Fill the DAO's
        this.mExecutors = executors;
    }

    /**
     *
     * If the database instance is provided null this method will create a dummy repository that serves test data
     * @param database AppDatabase instance
     * @param executors AppExecutors instance
     */
    public synchronized static AppRepository getInstance(@Nullable AppDatabase database, AppExecutors executors){
        if(database == null){
            Log.d(TAG, "Created new dummy repository");
            return new AppRepository();
        }
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new AppRepository(database, executors);
                Log.d(TAG, "Created new repository");
            }
        }
        return sInstance;
    }

    public boolean isDummy() {
        return isDummy;
    }
}

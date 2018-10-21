package com.example.abdullah.bookreader;

import android.content.Context;

import com.example.abdullah.bookreader.data.AppRepository;
import com.example.abdullah.bookreader.data.database.AppDatabase;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Class that holds injector methods
 */
public class InjectorUtils {
    //Injection methods should be like this.
    public static AppRepository provideRepository(Context context){
        AppDatabase database = AppDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        return AppRepository.getInstance(database, executors);
    }

    public static AppRepository provideDummyRepository(){
        return AppRepository.getInstance(null, null);

    }
}

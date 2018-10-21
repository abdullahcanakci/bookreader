package com.example.abdullah.bookreader;

import android.content.Context;

import com.example.abdullah.bookreader.data.AppRepository;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.data.database.AppDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class that holds injector methods
 */
public class InjectorUtils {
    //Injection methods should be like this.
    public static Repository provideRepository(Context context){
        AppDatabase database = provideAppdatabase(context);
        AppExecutors executors = provideAppExecutors();
        return AppRepository.getInstance(database, executors);
    }

    public static Repository provideDummyRepository(Context context){
        AppDatabase database = provideInRamAppDatabase(context);
        AppExecutors executors = provideAppExecutors();

        ArrayList<BookModel> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookModel b = new BookModel();
            b.setId(UUID.randomUUID().getLeastSignificantBits());
            b.setName("Book name " + i);
            temp.add(b);
        }

        AppRepository repo = AppRepository.getInstance(database, executors);
        repo.addBooks(temp);
        return repo;
    }

    public static AppDatabase provideAppdatabase(Context context){
        return AppDatabase.getInstance(context, true);
    }

    public static AppDatabase provideInRamAppDatabase(Context context){
        return AppDatabase.getInstance(context, false);
    }

    public static AppExecutors provideAppExecutors(){
        return AppExecutors.getInstance();
    }
}

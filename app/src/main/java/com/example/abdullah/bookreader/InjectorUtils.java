package com.example.abdullah.bookreader;

import android.content.Context;

import com.example.abdullah.bookreader.data.AppRepository;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.data.database.AppDatabase;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.ShelfBookJoinModel;
import com.example.abdullah.bookreader.data.models.ShelfModel;
import com.example.abdullah.bookreader.factories.FileExplorerViewModelFactory;
import com.example.abdullah.bookreader.factories.LandingPageViewModelFactory;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;

import java.util.ArrayList;
import java.util.Random;
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

    private static boolean isRun = false;
    public static Repository provideDummyRepository(Context context){
        AppDatabase database = provideInRamAppDatabase(context);
        AppExecutors executors = provideAppExecutors();
        AppRepository repo = AppRepository.getInstance(database, executors);
        if(isRun){
            return repo;
        }
        isRun = true;

        ArrayList<BookModel> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookModel b = new BookModel();
            b.setId(UUID.randomUUID().getLeastSignificantBits());
            b.setName("Book name Book name" + i);
            temp.add(b);
        }


        provideAppExecutors().diskIO().execute(() ->{
            Random r = new Random();
            for (int i = 1; i < 5; i++) {
                ShelfModel model = new ShelfModel();
                model.setName("Book Shelf " + i);
                long sId = database.getShelfDao().insert(model);
                for(int j = 0; j < new Random().nextInt(4) + 3; j++){
                    BookModel book = new BookModel();
                    book.setName("Book name " + i+j);
                    long time = System.currentTimeMillis();
                    book.setDate(time);
                    book.setInteractionDate(time - (Math.abs(r.nextLong())%10000));
                    book.setStatus(r.nextInt(100));
                    long bId = database.getBookDao().insert(book);
                    ShelfBookJoinModel m = new ShelfBookJoinModel(sId, bId);
                    database.getShelfBookJoinDao().insert(m);
                }
            }
        });
        return repo;
    }

    public static AppDatabase provideAppdatabase(Context context){
        return AppDatabase.getInstance(context, true);
    }

    public static AppDatabase provideInRamAppDatabase(Context context){
        return AppDatabase.getInstance(context, false);
    }

    private static MenuSelectionListener sMenuSelectionListener= null;
    public static void setMenuSelectionListener(MenuSelectionListener s) {
        sMenuSelectionListener = s;
    }

    public static MenuSelectionListener provideMenuSelectionListener(){
        return sMenuSelectionListener;
    }

    public static AppExecutors provideAppExecutors(){
        return AppExecutors.getInstance();
    }

    public static LandingPageViewModelFactory provideLandingPageViewModeFactory(Context context) {
        Repository repo = provideDummyRepository(context);
        return new LandingPageViewModelFactory(context, repo);

    }

    public static FileExplorerViewModelFactory provideFileExplorerViewModelFactory(Context context) {
        Repository repo = provideDummyRepository(context);
        return new FileExplorerViewModelFactory(context, repo);
    }
}

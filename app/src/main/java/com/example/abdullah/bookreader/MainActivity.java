package com.example.abdullah.bookreader;

import android.os.Bundle;
import android.util.Log;

import com.example.abdullah.bookreader.adapters.ShelfAdapter;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InjectorUtils.setMenuSelectionListener(new MenuSelectionListener() {
            @Override
            public void onShelfOpened(long shelfId) {
                Log.d(TAG, "onShelfOpened: id: "+ shelfId);
            }

            @Override
            public void onShelfEdited(long shelfId) {
                Log.d(TAG, "onShelfEdited: id: " + shelfId);
            }

            @Override
            public void onBookEdited(long id) {
                Log.d(TAG, "onBookEdited: id" + id);
            }

            @Override
            public void onBookOpened(long bookId) {
                Log.d(TAG, "onBookOpened: id: " + bookId);
            }
        });

        RecyclerView recycler = findViewById(R.id.canvas);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        ShelfAdapter adapter = new ShelfAdapter();

        recycler.setAdapter(adapter);

        Repository repo = InjectorUtils.provideDummyRepository(getApplicationContext());

        repo.getShelvesForDisplay().observe(this, (shelves) ->{
            adapter.updateList(shelves);
        });





    }
}

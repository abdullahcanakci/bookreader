package com.example.abdullah.bookreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.abdullah.bookreader.adapters.ShelfAdapter;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final int TAG_PERMISSION_EXTRENAL_WRITE = 100;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();

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

    private void requestPermissions(){
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    TAG_PERMISSION_EXTRENAL_WRITE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

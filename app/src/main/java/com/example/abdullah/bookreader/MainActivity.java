package com.example.abdullah.bookreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.fragments.FileExplorerFragment;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity{
    private static final int TAG_PERMISSION_EXTERNAL_WRITE = 100;
    private static final int TAG_PERMISSION_EXTERNAL_READ = 101;
    private static String TAG = "MainActivity";

    FileExplorerFragment explorer;

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

        Repository repo = InjectorUtils.provideDummyRepository(getApplicationContext());
        repo.getFileModels().observe(this, (files) -> {
            if(!files.isEmpty()) {
                Toast.makeText(this, "NumberOfItems: " + files.size(), Toast.LENGTH_SHORT).show();
                repo.deleteFileModels(files);
            }
        });
        explorer = FileExplorerFragment.getInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.canvas, explorer).commit();
    }

    @Override
    public void onBackPressed() {
        if(explorer.goBack()){
            return;
        }
        super.onBackPressed();
    }

    private void requestPermissions(){
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    TAG_PERMISSION_EXTERNAL_WRITE);
        }
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    TAG_PERMISSION_EXTERNAL_READ);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

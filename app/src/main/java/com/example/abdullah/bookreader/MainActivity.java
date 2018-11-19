package com.example.abdullah.bookreader;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.FileModel;
import com.example.abdullah.bookreader.fragments.FileExplorerFragment;
import com.example.abdullah.bookreader.helpers.FileSelectionHelper;
import com.example.abdullah.bookreader.helpers.FragmentType;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;
import com.example.abdullah.bookreader.listeners.NavigationListener;
import com.example.abdullah.bookreader.utils.NavigationHandler;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity{
    private static final int TAG_PERMISSION_EXTERNAL_WRITE = 100;
    private static final int TAG_PERMISSION_EXTERNAL_READ = 101;
    private static final int PERMISSION_ALL = 99;
    private static String TAG = "MainActivity";

    private FileExplorerFragment explorer;
    private static NavigationHandler navigationHandler;
    private Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        repo = InjectorUtils.provideDummyRepository(getApplicationContext());

        InjectorUtils.setNavigationHandler(
                new NavigationHandler(
                        findViewById(R.id.canvas),
                        getSupportFragmentManager()
                )
        );
        navigationHandler = InjectorUtils.provideNavigationHandler();
        ((NavigationListener) navigationHandler).goTo(FragmentType.LANDING_FRAGMENT);
        
        repo.getFileModels().observe(this, (files) -> {
            handleFileInput(files);
        });
    }

    /**
     * This will be called when user selects files and confirms their selection.
     * App should confirm they are legit files that can be handled and
     * handles {@link FileModel} -> {@link BookModel} conversion and
     * @param models selected by user
     */
    private void handleFileInput(List<FileModel> models){
        if(models != null && !models.isEmpty()){
            List<BookModel> books = FileSelectionHelper.convertToBookModels(models);
            if(books == null || books.isEmpty()){
                Toast.makeText(this, R.string.book_import_empty, Toast.LENGTH_SHORT).show();
            } else {
                repo.addBooks(books);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(navigationHandler.goBack()){
            return;
        }
        super.onBackPressed();
    }

    private boolean hasPermissions(Context context, String... permissions){
        if(context != null && permissions != null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    private void requestPermissions(){
        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

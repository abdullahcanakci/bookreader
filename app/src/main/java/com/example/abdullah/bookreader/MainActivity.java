package com.example.abdullah.bookreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.fragments.FileExplorerFragment;
import com.example.abdullah.bookreader.helpers.FragmentType;
import com.example.abdullah.bookreader.listeners.MenuSelectionListener;
import com.example.abdullah.bookreader.listeners.NavigationListener;
import com.example.abdullah.bookreader.utils.NavigationHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity{
    private static final int TAG_PERMISSION_EXTERNAL_WRITE = 100;
    private static final int TAG_PERMISSION_EXTERNAL_READ = 101;
    private static String TAG = "MainActivity";

    private FileExplorerFragment explorer;
    private static NavigationHandler navigationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        Repository repo = InjectorUtils.provideDummyRepository(getApplicationContext());

        InjectorUtils.setNavigationHandler(
                new NavigationHandler(
                        findViewById(R.id.canvas),
                        getSupportFragmentManager()
                )
        );
        navigationHandler = InjectorUtils.provideNavigationHandler();
        ((NavigationListener) navigationHandler).goTo(FragmentType.LANDING_FRAGMENT);
    }

    @Override
    public void onBackPressed() {
        if(navigationHandler.goBack()){
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

package com.example.abdullah.bookreader;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.abdullah.bookreader.adapters.ShelfAdapter;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.data.models.ShelfModel;
import com.example.abdullah.bookreader.databinding.CardShelfBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.canvas);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        ShelfAdapter adapter = new ShelfAdapter();
        recycler.setAdapter(adapter);

        Repository repo = InjectorUtils.provideDummyRepository(getApplicationContext());

        repo.getShelves().observe(this, (shelves) ->{
            adapter.updateList(shelves);
        });





    }
}

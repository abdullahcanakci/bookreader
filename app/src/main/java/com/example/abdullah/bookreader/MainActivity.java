package com.example.abdullah.bookreader;

import android.os.Bundle;

import com.example.abdullah.bookreader.adapters.BookAdapter;
import com.example.abdullah.bookreader.data.AppRepository;
import com.example.abdullah.bookreader.data.Repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookAdapter adapter = new BookAdapter();

        Repository repo = InjectorUtils.provideDummyRepository(getApplicationContext());
        repo.getAllBooks().observe(this, adapter::updateList);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(adapter);

    }
}

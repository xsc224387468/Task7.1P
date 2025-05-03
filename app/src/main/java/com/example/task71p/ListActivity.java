package com.example.task71p;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListActivity extends Activity {
    private ListView listView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);
        loadList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void loadList() {
        Cursor cursor = dbHelper.getAllAdverts();
        String[] from = {"description"};
        int[] to = {R.id.tvDescription};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_item_advert, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }
} 
package com.example.task71p;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
    private TextView tvDetail;
    private Button btnRemove;
    private DatabaseHelper dbHelper;
    private long advertId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetail = findViewById(R.id.tvDetail);
        btnRemove = findViewById(R.id.btnRemove);
        dbHelper = new DatabaseHelper(this);

        advertId = getIntent().getLongExtra("id", -1);
        loadDetail();

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAdvert(advertId);
                Toast.makeText(DetailActivity.this, "Advert removed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void loadDetail() {
        Cursor cursor = dbHelper.getAdvertById(advertId);
        if (cursor.moveToFirst()) {
            StringBuilder sb = new StringBuilder();
            sb.append(cursor.getString(cursor.getColumnIndexOrThrow("type"))).append(" ");
            sb.append(cursor.getString(cursor.getColumnIndexOrThrow("description"))).append("\n");
            sb.append(cursor.getString(cursor.getColumnIndexOrThrow("date"))).append("\n");
            sb.append(cursor.getString(cursor.getColumnIndexOrThrow("location")));
            tvDetail.setText(sb.toString());
        }
        cursor.close();
    }
} 
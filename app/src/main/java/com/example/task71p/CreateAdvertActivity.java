package com.example.task71p;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateAdvertActivity extends Activity {
    private EditText etName, etPhone, etDescription, etDate, etLocation;
    private RadioGroup rgType;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        rgType = findViewById(R.id.rgType);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgType.getCheckedRadioButtonId();
                RadioButton rbType = findViewById(selectedId);
                String type = rbType.getText().toString();
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String description = etDescription.getText().toString();
                String date = etDate.getText().toString();
                String location = etLocation.getText().toString();
                if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
                    Toast.makeText(CreateAdvertActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHelper.insertAdvert(type, name, phone, description, date, location);
                Toast.makeText(CreateAdvertActivity.this, "Advert saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
} 
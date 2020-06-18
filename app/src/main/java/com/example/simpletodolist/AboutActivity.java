package com.example.simpletodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button btnXong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        addControllers();
        addEvents();
    }

    private void addEvents() {
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXong();
            }
        });
    }

    private void xuLyXong() {
        finish();
    }

    private void addControllers() {
        btnXong = findViewById(R.id.btnXong);
    }
}

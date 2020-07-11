package com.example.simpletodolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.model.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TodoActivity extends AppCompatActivity {
    EditText txtTenCongViec, txtMoTaCongViec;
    TextView txtThoiHan, txtThoiGian;
    ImageButton btnThoiHan, btnThoiGian;
    Button btnXong, btnHuy;
    int position = -1;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        addControllers();
        addEvents();
    }

    private void addControllers() {
        txtTenCongViec = findViewById(R.id.txtTenCongViec);
        txtMoTaCongViec = findViewById(R.id.txtMoTaCongViec);
        txtThoiHan = findViewById(R.id.txtThoiHan);
        txtThoiGian = findViewById(R.id.txtThoiGian);
        btnThoiHan = findViewById(R.id.btnThoiHan);
        btnThoiGian = findViewById(R.id.btnThoiGian);
        btnXong = findViewById(R.id.btnXong);
        btnHuy = findViewById(R.id.btnHuy);

        Calendar calendar = Calendar.getInstance();
        txtThoiHan.setText(sdf1.format(calendar.getTime()));
        txtThoiGian.setText(sdf2.format(calendar.getTime()));

        //Hiển thị khi nhấn chỉnh sửa
        txtTenCongViec.setText(getIntent().getStringExtra("chinhSuaTenCongViec"));
        txtMoTaCongViec.setText(getIntent().getStringExtra("chinhSuaMoTaCongViec"));
        txtThoiHan.setText(getIntent().getStringExtra("chinhSuaThoiHan"));
        txtThoiGian.setText(getIntent().getStringExtra("chinhSuaThoiGian"));

    }

    private void addEvents() {

        btnThoiHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTheHienDatePicker();
            }
        });

        btnThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTheHienTimePicker();
            }
        });

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuToDo();
            }
        });
    }

    private void xuLyLuuToDo() {
        int pos = getIntent().getIntExtra("viTri", 0);

        Intent intent = new Intent(TodoActivity.this, MainActivity.class);
        intent.putExtra("tenCongViec", txtTenCongViec.getText().toString());
        intent.putExtra("moTaCongViec", txtMoTaCongViec.getText().toString());
        intent.putExtra("thoiHan", txtThoiHan.getText().toString());
        intent.putExtra("thoiGian", txtThoiGian.getText().toString());
        intent.putExtra("viTriSua", pos);
        setResult(13, intent); // mã này cũng tùy ý
        finish(); //đóng màn hình lại
    }

    private void xuLyTheHienDatePicker() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                txtThoiHan.setText(sdf1.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                TodoActivity.this,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    private void xuLyTheHienTimePicker() {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                txtThoiGian.setText(sdf2.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                TodoActivity.this,
                callBack,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    public void Huy(View view) {
        finish();
    }

}

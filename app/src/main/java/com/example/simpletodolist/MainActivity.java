package com.example.simpletodolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.adapter.TodoAdapter;
import com.example.model.Todo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem;

    ListView lvTodo;
    ArrayList<Todo> dsTodo;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControllers();
        addEvents();
    }

    private void addControllers() {
        btnThem = findViewById(R.id.btnThem);
        lvTodo = findViewById(R.id.lvTodo);

        lvTodo = findViewById(R.id.lvTodo);
        dsTodo = new ArrayList<>();
        todoAdapter = new TodoAdapter(MainActivity.this, R.layout.item, dsTodo);
        lvTodo.setAdapter(todoAdapter);
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLayKetQua();
            }
        });
    }

    private void xuLyLayKetQua() {
        Intent intent = new Intent(MainActivity.this, TodoActivity.class);
        startActivityForResult(intent, 99); //99: mã là gì cũng được
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuThongTin){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 13 && requestCode == 99){
            String tenCongViec = data.getStringExtra("tenCongViec");
            String moTaCongViec = data.getStringExtra("moTaCongViec");
            String thoiHan = data.getStringExtra("thoiHan");
            String thoiGian = data.getStringExtra("thoiGian");

            Todo todo = new Todo(tenCongViec, moTaCongViec, thoiHan, thoiGian);
            dsTodo.add(todo);
            todoAdapter.notifyDataSetChanged();
        }
    }
}

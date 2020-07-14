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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.*;
import android.widget.Toast;
import com.example.adapter.TodoAdapter;
import com.example.model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
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
        fabAdd = findViewById(R.id.fabAdd);
        lvTodo = findViewById(R.id.lvTodo);

        lvTodo = findViewById(R.id.lvTodo);
        dsTodo = new ArrayList<>();
        todoAdapter = new TodoAdapter(MainActivity.this, R.layout.item, dsTodo);
        lvTodo.setAdapter(todoAdapter);
    }

    private void addEvents() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLayKetQua();
            }
        });
        lvTodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
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
//        if(item.getItemId() == R.id.menuNgonNgu){
//
//        }
        if(item.getItemId() == R.id.menuSapxep){
            Collections.sort(dsTodo, new Comparator<Todo>() {
                @Override
                public int compare(Todo o1, Todo o2) {
                    return o1.getThoiHan().compareTo(o2.getThoiHan());
                }
            });
            todoAdapter.notifyDataSetChanged(); //reload lại mang de sap xep
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

        if(resultCode == 13 && requestCode == 98){ //xu ly phan sua thong tin
            String tenCongViec = data.getStringExtra("tenCongViec");
            String moTaCongViec = data.getStringExtra("moTaCongViec");
            String thoiHan = data.getStringExtra("thoiHan");
            String thoiGian = data.getStringExtra("thoiGian");
            int vitri = data.getIntExtra("viTriSua", 0);

            Todo todo2 = new Todo(tenCongViec, moTaCongViec, thoiHan, thoiGian);
            dsTodo.set(vitri, todo2);
            todoAdapter.notifyDataSetChanged();
        }
    }
}

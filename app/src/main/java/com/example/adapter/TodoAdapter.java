package com.example.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.model.Todo;
import com.example.simpletodolist.MainActivity;
import com.example.simpletodolist.R;
import com.example.simpletodolist.TodoActivity;
import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todo> {
    @NonNull
    Activity context;
    int resource;
    @NonNull
    List<Todo> objects;

    public TodoAdapter(@NonNull Activity context, int resource, @NonNull List<Todo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    // set phan hien thi tren listview
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView txtTenCongViec = row.findViewById(R.id.txtTenCongViec);
        TextView txtMoTaCongViec = row.findViewById(R.id.txtMoTaCongViec);
        TextView txtThoiHan = row.findViewById(R.id.txtThoiHan);
        TextView txtThoiGian = row.findViewById(R.id.txtThoiGian);
        ImageButton btnChinhSua = row.findViewById(R.id.btnChinhSua);
        ImageButton btnXoa = row.findViewById(R.id.btnXoa);

        final Todo todo = this.objects.get(position);
        txtTenCongViec.setText(todo.getTenCongViec());
        //txtMoTaCongViec.setText(todo.getMoTaCongViec());
        txtThoiHan.setText(todo.getThoiHan());
        txtThoiGian.setText(todo.getThoiGian());

        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChinhSua(todo, position);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoa(todo);
            }
        });


        return row;
    }



    private void xuLyChinhSua(Todo todo, int position) {
        Toast.makeText(context, "Number: " + position, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this.context, TodoActivity.class);
        intent.putExtra("chinhSuaTenCongViec", todo.getTenCongViec());
        intent.putExtra("chinhSuaMoTaCongViec", todo.getMoTaCongViec());
        intent.putExtra("chinhSuaThoiHan", todo.getThoiHan());
        intent.putExtra("chinhSuaThoiGian", todo.getThoiGian());
        intent.putExtra("viTri", position);
        context.startActivityForResult(intent, 98); // mã là gì cũng được

    }

    private void xuLyXoa(Todo todo) {
        this.remove(todo);
    }
}

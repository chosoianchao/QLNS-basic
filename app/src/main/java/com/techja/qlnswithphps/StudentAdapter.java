package com.techja.qlnswithphps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private MainActivity context;
    private final int layout;
    private final List<Student> list;

    public StudentAdapter(MainActivity context, int layout, List<Student> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            holder.txtvHoTen = view.findViewById(R.id.txtvHoTen);
            holder.txtvNamSinh = view.findViewById(R.id.txtvNamSinh);
            holder.txtvDiaChi = view.findViewById(R.id.txtvDiaChi);
            holder.imgEdit = view.findViewById(R.id.imgEdit);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Student student = list.get(i);
        holder.txtvHoTen.setText(student.getHoTen());
        holder.txtvNamSinh.setText(student.getNamSinh());
        holder.txtvDiaChi.setText(student.getDiaChi());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditStudentActivity.class);
                intent.putExtra("studentEdit", student);
                context.startActivity(intent);


            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelte(student.getHoTen(),student.getId());

            }
        });
        return view;
    }

    private void confirmDelte(String hoten,final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo!");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Bạn thật sự muốn xóa " + hoten + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.deleteStudent(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    class ViewHolder {
        TextView txtvHoTen, txtvNamSinh, txtvDiaChi;
        ImageView imgEdit, imgDelete;
    }
}

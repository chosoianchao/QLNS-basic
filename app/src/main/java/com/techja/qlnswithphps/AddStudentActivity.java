package com.techja.qlnswithphps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {
    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mapping();
    }

    private void mapping() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtNamSinh = findViewById(R.id.edtNamSinh);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }

    public void Add(View view) {
        String hoTen = edtHoTen.getText().toString().trim();
        String namSinh = edtNamSinh.getText().toString().trim();
        String diaChi = edtDiaChi.getText().toString().trim();
        if (hoTen.equals("") || namSinh.matches("") || diaChi.isEmpty()) {
            if (hoTen.equals("")) {
                Toast.makeText(this, "Bạn phải nhập họ tên", Toast.LENGTH_SHORT).show();
                edtHoTen.requestFocus();
            } else if (namSinh.equals("")) {
                Toast.makeText(this, "Bạn phải nhập năm sinh", Toast.LENGTH_SHORT).show();
                edtNamSinh.requestFocus();
            } else if (diaChi.equals("")) {
                Toast.makeText(this, "Bạn phải nhập địa chỉ", Toast.LENGTH_SHORT).show();
                edtDiaChi.requestFocus();
            }

        } else {
            //viết hàm ở đây
            insertStudent("http://192.168.32.102:4430/69dcht22/insertdata.php");
        }
    }

    private void insertStudent(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(AddStudentActivity.this, response + ",Thêm mới thành công!!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddStudentActivity.this, response + ",Thêm mới không thành công!!", Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(AddStudentActivity.this, MainActivity.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddStudentActivity.this, error.toString() + ",Có lỗi xảy ra trong quá trình thêm thông tin", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("hoTen", edtHoTen.getText().toString());
                map.put("namSinh", edtNamSinh.getText().toString());
                map.put("diaChi", edtDiaChi.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void Cancel(View view) {
        finish();

    }
}
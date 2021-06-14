package com.techja.qlnswithphps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

public class EditStudentActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnEdit, btnCancel;
    int idEdit = -1;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_edit_student);
        mapping();
        btnEdit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("studentEdit");
        edtHoTen.setText(student.getHoTen());
        edtNamSinh.setText(student.getNamSinh());
        edtDiaChi.setText(student.getDiaChi());
        idEdit = student.getId();
    }


    private void mapping() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtNamSinh = findViewById(R.id.edtNamSinh);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit:
                updateStudent("http://192.168.32.102:4430/69dcht22/updatedata.php");
                break;
            case R.id.btnCancel:
                break;
        }
    }

    private void updateStudent(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(EditStudentActivity.this, response + ", Sửa thành công !!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditStudentActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(EditStudentActivity.this, response + ", Sửa không thành công !!", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditStudentActivity.this, error.toString() + ", Có lỗi xảy trong quá trình !!", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iD", String.valueOf(idEdit));
                map.put("hoTen", edtHoTen.getText().toString().trim());
                map.put("namSinh", edtNamSinh.getText().toString().trim());
                map.put("diaChi", edtDiaChi.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}


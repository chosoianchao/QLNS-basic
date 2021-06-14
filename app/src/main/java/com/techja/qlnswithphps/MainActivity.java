package com.techja.qlnswithphps;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lvStudent;
    Menu mnuAddStudent;
    List<Student> studentList;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = findViewById(R.id.lvStudent);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(MainActivity.this, R.layout.activity_line_student, studentList);

        lvStudent.setAdapter(adapter);
        getAllStudent();
    }

    public void getAllStudent() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.32.102:4430/69dcht22/getalldata.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                studentList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        studentList.add(new Student(jsonObject.getInt("iD"), jsonObject.getString("hoTen"), jsonObject.getString("ngaySinh"), jsonObject.getString("diaChi")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuAddStudent) ;
        {
            startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteStudent(final int id) {
        String url = "http://192.168.32.102:4430/69dcht22/deletedata.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(MainActivity.this, response + "Xóa thành công!!", Toast.LENGTH_LONG).show();
                    getAllStudent();
                } else {
                    Toast.makeText(MainActivity.this, response + "Xóa không thành công!!", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString() + "Có lỗi xảy ra!!", Toast.LENGTH_LONG).show();
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iD", String.valueOf(id));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}


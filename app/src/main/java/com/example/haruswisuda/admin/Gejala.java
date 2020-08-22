package com.example.haruswisuda.admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haruswisuda.API;
import com.example.haruswisuda.AdapterPenyakit;
import com.example.haruswisuda.ModelPenyakit;
import com.example.haruswisuda.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Gejala extends AppCompatActivity {
    Bundle bundle;
    TextView textView;
    Button btnAct;
    Spinner penyakit, gejala;
    AdapterSpinnerPenyakit adapter;
    API api = new API();
    String url;
    RecyclerView recyclerView;
    List<ModelSPinner> list  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala_admin);
        bundle = getIntent().getExtras();
        textView = findViewById(R.id.txtKet);
        btnAct = findViewById(R.id.btnAction);
        url = api.getApi_service();
        penyakit = findViewById(R.id.spinnerPenyakit);
        gejala = findViewById(R.id.spinnerGejala);
        recyclerView = findViewById(R.id.recySpiner);
        String act = bundle.getString("req");

        if(act=="tambah"){
            btnAct.setText("Tambah");
            textView.setText("Tambah Gejala");
            ambilPenyakit();
        }
        else if(act=="tambah"){
            btnAct.setText("Edit");
            textView.setText("Edit Gejala");
        }
        else if(act=="tambah"){
            textView.setText("Hapus Gejala");
            btnAct.setText("Hapus");
        }
    }
    public void ambilPenyakit(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "/GetData/?service=penyakit", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        ModelSPinner modelPenyakit = new ModelSPinner(jsonObject1.getString("id_penyakit"), jsonObject1.getString("nama_penyakit"));
                        list.add(modelPenyakit);
                    }
                    adapter = new AdapterSpinnerPenyakit(list, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
package com.example.haruswisuda;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class info extends AppCompatActivity {
    RecyclerView recyclerView;
    API api = new API();
    AdapterPenyakit adapterPenyakit;
    List<ModelPenyakit> list  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        api=new API();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getData();
    }
    public void getData() {
        StringRequest penyakit = new StringRequest(Request.Method.GET, api.getApi_service()+"GetData/?service=penyakit", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAGRES", "onResponse: "+response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        ModelPenyakit modelPenyakit = new ModelPenyakit(jsonObject1.getString("id_penyakit"), jsonObject1.getString("nama_penyakit"), jsonObject1.getString("deskripsi"));
                        list.add(modelPenyakit);
                    }
                    adapterPenyakit = new AdapterPenyakit(list, getApplicationContext());
                    recyclerView.setAdapter(adapterPenyakit);
                    adapterPenyakit.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: "+error);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(penyakit);
    }

}

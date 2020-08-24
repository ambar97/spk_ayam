package com.example.haruswisuda;

import android.app.ProgressDialog;
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
    AdapterInfo adapterInfo;
    List<ModelInfo> list  = new ArrayList<>();
    String [] imgs ={"snot","ngorokkk","ilt","berakkapur","gumboro","tetelo"};
    ProgressDialog pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        api=new API();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getData();
        pg= new ProgressDialog(this);
        pg.setMessage("Tunggu");
        pg.setIndeterminate(false);
        pg.setCancelable(false);
        pg.show();
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
                        ModelInfo Modelinfo = new ModelInfo(jsonObject1.getString("id_penyakit"), jsonObject1.getString("nama_penyakit"), jsonObject1.getString("deskripsi"),imgs[i]);
                        list.add(Modelinfo);
                    }
                    adapterInfo = new AdapterInfo(list,info.this);
                    recyclerView.setAdapter(adapterInfo);
                    adapterInfo.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    pg.dismiss();
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

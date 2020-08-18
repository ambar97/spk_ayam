package com.example.haruswisuda;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class Gejala extends AppCompatActivity {

    TextView tvGetNamaPenyakit;
    Button btnCekHasil;
    List<ModelGejala> gejalas = new ArrayList<>();
    AdapterGejala adapterGejala;
    RecyclerView recyclerView;
    Bundle bundle;
    public static List<String> integerList = new ArrayList<>();
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        api=new API();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvGetNamaPenyakit = (TextView) findViewById(R.id.textView1);
        tvGetNamaPenyakit.setText(bundle.getString("nama"));
        btnCekHasil = findViewById(R.id.btnAnalis);
        recyclerView = findViewById(R.id.recyGejala);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getGejala();
        btnCekHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean gas = Boolean.FALSE;
                for (int i=0; i<integerList.size(); i++) {
                    if (integerList.get(i).equals("-2")) {
                        Toast.makeText(Gejala.this, "Harap isi Gejala", Toast.LENGTH_LONG).show();
                        break;
                    }
                    gas = (i == integerList.size()-1 ) ? Boolean.TRUE : Boolean.FALSE;
                }
                if (gas) {
                    sendGejala();
                }
            }
        });

    }
    public void getGejala(){
        StringRequest penyakit = new StringRequest(Request.Method.GET, api.getApi_service()+"Diagnosa/?serv=getDataDiagnosa&id_penyakit="+bundle.getString("id"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAGRES", "onResponse: "+response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        ModelGejala modelGejala = new ModelGejala(jsonObject1.getString("id_gejala"), "Apakah Ayam Mengalami "+jsonObject1.getString("nama_gejala")+" ?");
                        gejalas.add(modelGejala);
                        integerList.add("-2");
                    }
                    adapterGejala = new AdapterGejala(gejalas, getApplicationContext());
                    recyclerView.setAdapter(adapterGejala);
                    adapterGejala.notifyDataSetChanged();
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

    public void sendGejala(){
        StringRequest penyakit = new StringRequest(Request.Method.POST, "????", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAGRES", "onResponse: "+response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        ModelGejala modelGejala = new ModelGejala(jsonObject1.getString("id_gejala"), "Apakah Ayam Mengalami "+jsonObject1.getString("nama_gejala")+" ?");
                        gejalas.add(modelGejala);
                        integerList.add("-2");
                    }
                    adapterGejala = new AdapterGejala(gejalas, getApplicationContext());
                    recyclerView.setAdapter(adapterGejala);
                    adapterGejala.notifyDataSetChanged();
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

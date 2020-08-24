package com.example.haruswisuda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gejala extends AppCompatActivity {
    ProgressDialog dialog;
    TextView tvGetNamaPenyakit;
    String hsailbro;
    Button btnCekHasil;
    List<ModelGejala> gejalas = new ArrayList<>();
    AdapterGejala adapterGejala;
    RecyclerView recyclerView;
    Bundle bundle;
    public static List<String> integerList ;
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        api=new API();

        dialog=new ProgressDialog(this);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setMessage("Memproses ...");

        integerList = new ArrayList<>();

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
                        Snackbar snackbar = Snackbar
                                .make(view, "Harap Pilih Kepastian Gejala", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                    }else {
                        gas = (i == integerList.size() - 1) ? Boolean.TRUE : Boolean.FALSE;
                    }
                }
                if (gas) {
                    sendGejala();
                    Log.d("TAG", "onClick: "+integerList);
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
        dialog.show();
        StringRequest penyakit = new StringRequest(Request.Method.POST, api.getApi_service()+"diagnosa/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                     hsailbro= jsonObject.getString("hasil");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("penyakit");
                    dialog.dismiss();
                    startActivity(new Intent(Gejala.this,ListdataActivity.class).putExtra("hasil",hsailbro).putExtra("nama", jsonObject1.getString("nama_penyakit")
                    ).putExtra("des",jsonObject1.getString("deskripsi")).putExtra("img",bundle.getString("img")));
                    finish();
                } catch (JSONException e) {
                    Log.d("TAG", "onResponse: "+response);
                    Toast.makeText(Gejala.this, "Gagal,coba lagi", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: "+error);
            }
        }){
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                JSONObject jsonObject = new JSONObject();
                try {
                    for (int i = 0; i <integerList.size(); i++){
                        jsonObject.put("data"+i, integerList.get(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Map<String, String> param = new HashMap<>();
//                param.put("api","diagnosa");
                param.put("cf",jsonObject.toString());
                Log.d("TAG", "getParams: ");
                param.put("id_penyakit", bundle.getString("id"));
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(penyakit);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

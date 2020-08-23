package com.example.haruswisuda.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haruswisuda.API;
import com.example.haruswisuda.Gejala;
import com.example.haruswisuda.ListdataActivity;
import com.example.haruswisuda.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GejalaAdmin extends AppCompatActivity {
    Bundle bundle;
    TextView textView;
    Button btnAct;
    EditText editText, nilai;
    Spinner penyakit, gejala;
    API api = new API();
    String url;
    List<String> listidGejala  = new ArrayList<>();
    List<String> listgejala  = new ArrayList<>();
    int position = 0;
    String posGejala;
    String [] namaPenyakit ={"Pilih Penyakit","Penyakit Snot (Coryza)","Penyakit Ngorok (CDR)","Penyakit Infectious Laryngotracheitis (ILT)","Penyakit Berak Kapur (Pullorum)","Penyakit Gumoro","Penyakit Tetelo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala_admin);
        bundle = getIntent().getExtras();
        textView = findViewById(R.id.txtKet);
        btnAct = findViewById(R.id.btnAction);
        editText=findViewById(R.id.gejalaadmin);
        url = api.getApi_service();
        nilai = findViewById(R.id.nilaipakaradmin);
        penyakit = findViewById(R.id.spinnerPenyakit);
        gejala = findViewById(R.id.spinnerGejala);

        if (bundle.getString("req").equals("tambah")){
            textView.setText("Tambah Gejala");
            btnAct.setText("Tambah Gejala");
            gejala.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
            editText.setHint("Masukan Gejala Baru");
            nilai.setVisibility(View.VISIBLE);
            nilai.setHint("Masukan nilai pakar");
            btnAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tambahGejala();
                }
            });

        } else if (bundle.getString("req").equals("hapus")) {
            textView.setText("Hapus Gejala");
            btnAct.setText("Hapus Gejala");
            btnAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hapusGejala();
                }
            });
        }

        final ArrayAdapter<String> adapterPenyakit = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, namaPenyakit);
        adapterPenyakit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        penyakit.setAdapter(adapterPenyakit);
        penyakit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                Log.d("TAG", "poss: "+i);
                setSpinnerGejala(gejala,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gejala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                posGejala = listidGejala.get(i);
                Log.d("TAG", "postid: "+posGejala);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void setSpinnerGejala(final Spinner jenis, int id) {
        listgejala.clear();
        listidGejala.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "Diagnosa/?serv=getDataDiagnosa&id_penyakit="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject gejala = jsonArray.getJSONObject(i);
                        listgejala.add(gejala.getString("nama_gejala"));
                        listidGejala.add(gejala.getString("id_gejala"));
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(GejalaAdmin.this, android.R.layout.simple_spinner_item, listgejala);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    jenis.setAdapter(dataAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("P", "onErrorResponse: ", error);
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void tambahGejala(){
        StringRequest penyakit = new StringRequest(Request.Method.POST, api.getApi_service()+"Admin/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String berhasil = jsonObject.getString("hasil");
                    if (berhasil.equals("sukses")){
                        Toast.makeText(GejalaAdmin.this,"Data Berhasil Di tambah",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    Log.d("TAG", "onResponse: "+response);
                    Toast.makeText(GejalaAdmin.this, "Gagal,coba lagi", Toast.LENGTH_SHORT).show();
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
                Map<String, String> param = new HashMap<>();
                Log.d("TAG", "getParams: ");
                param.put("bobot", nilai.getText().toString());
                param.put("nama_gejala", editText.getText().toString());
                param.put("id", String.valueOf(position));
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(penyakit);
    }
    public void hapusGejala(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "Admin/?admin=deleteGejala&id_gejala="+posGejala+"&id_p="+position, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String sip = jsonObject.getString("hasil");
                    if (sip.equals("ok")){
                        Toast.makeText(GejalaAdmin.this,jsonObject.getString("alert"),Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(GejalaAdmin.this,"data gagal di hapus, coba lagi",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("P", "onErrorResponse: ", error);
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
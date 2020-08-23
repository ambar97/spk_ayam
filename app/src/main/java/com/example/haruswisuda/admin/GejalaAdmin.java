package com.example.haruswisuda.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haruswisuda.API;
import com.example.haruswisuda.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GejalaAdmin extends AppCompatActivity {
    Bundle bundle;
    TextView textView;
    Button btnAct;
    EditText editText;
    Spinner penyakit, gejala;
    API api = new API();
    String url;
    List<String> listidGejala  = new ArrayList<>();
    List<String> listgejala  = new ArrayList<>();

    String [] namaPenyakit ={"Pilih Penyakit","Penyakit Snot (Coryza)","Penyakit Ngorok (CDR)","Penyakit Infectious Laryngotracheitis (ILT)","Penyakit Berak Kapur (Pullorum)","Penyakit Gumoro","Penyakit Tetelo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala_admin);
        bundle = getIntent().getExtras();
        textView = findViewById(R.id.txtKet);
        btnAct = findViewById(R.id.btnAction);
        editText=findViewById(R.id.edittextview);
        url = api.getApi_service();
        penyakit = findViewById(R.id.spinnerPenyakit);
        gejala = findViewById(R.id.spinnerGejala);

        if (bundle.getString("req").equals("tambah")){
            textView.setText("Tambah Gejala");
            btnAct.setText("Tambah Gejala");
            gejala.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
            editText.setHint("Masukan Gejala Baru");
            btnAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tambahGejala();
                }
            });
        }else if (bundle.getString("req").equals("edit")){
            textView.setText("Edit Gejala");
            btnAct.setText("Edit Gejala");
            editText.setVisibility(View.VISIBLE);
            editText.setHint("Ubah Gejala");
            btnAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editGejala();
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


    }
    private void setSpinnerGejala(final Spinner jenis, int id) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "Diagnosa/?serv=getDataDiagnosa&id_penyakit="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject gejala = jsonArray.getJSONObject(i);
                        listgejala.add(gejala.getString("name"));
                        listidGejala.add(gejala.getString("id"));
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

    }
    public void editGejala(){

    }
    public void hapusGejala(){

    }

}
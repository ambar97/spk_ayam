package com.example.haruswisuda.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haruswisuda.API;
import com.example.haruswisuda.R;

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

    String [] namaPenyakit ={"Penyakit Snot (Coryza)","Penyakit Ngorok (CDR)","Penyakit Infectious Laryngotracheitis (ILT)","Penyakit Berak Kapur (Pullorum)","Penyakit Gumoro","Penyakit Tetelo"};

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
//    private void setSpinnerGejala(final Spinner jenis) {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, api + "GETGEJALASEMUA", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray banyakdata = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < banyakdata.length(); i++) {
//                        JSONObject kategori = banyakdata.getJSONObject(i);
//                        list.add(kategori.getString("name"));
//                        listIdKate.add(kategori.getString("id"));
//                    }
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCategoris);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    jenis.setAdapter(dataAdapter);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("P", "onErrorResponse: ", error);
//                    }
//                }
//        );
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
    public void tambahGejala(){

    }
    public void editGejala(){

    }
    public void hapusGejala(){

    }

}
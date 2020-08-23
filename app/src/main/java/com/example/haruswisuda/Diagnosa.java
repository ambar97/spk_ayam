package com.example.haruswisuda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Diagnosa extends AppCompatActivity {

    EditText edittextview;
    Button btnAnalisa;
    String kalimat, img;
    TextView id_penyakit;

    String [] namaPenyakit ={"Penyakit Snot (Coryza)","Penyakit Ngorok (CDR)","Penyakit Infectious Laryngotracheitis (ILT)","Penyakit Berak Kapur (Pullorum)","Penyakit Gumoro","Penyakit Tetelo"};
    String [] imgs ={"snot","ngorokkk","ilt","berakkapur","gumboro","tetelo"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edittextview = (EditText) findViewById(R.id.edittextview);
        btnAnalisa = (Button) findViewById(R.id.btnAnalisa);
        id_penyakit= findViewById(R.id.id_penyakit);

        final ArrayAdapter<String> adapterjenis = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, namaPenyakit);


        edittextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Diagnosa.this).setTitle("Pilih Nama Penyakit :").setAdapter(adapterjenis, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = i+1;
                        edittextview.setText(namaPenyakit[i].toString());
                        id_penyakit.setText(""+id);
                        img=imgs[i];
                        dialogInterface.dismiss();
                    }
                }).create().show();
            }
        });

        btnAnalisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kalimat = edittextview.getText().toString();
                if (kalimat.equals("Pilih Diagnosa Nama Penyakit")) {
                    showToashMessege("Nama Penyakit belum dimasukan");
                    edittextview.requestFocus();
                } else {
                    Intent intent = new Intent(Diagnosa.this, Gejala.class);
                    intent.putExtra("nama", kalimat);
                    intent.putExtra("id", id_penyakit.getText());
                    intent.putExtra("img",img);
                    startActivity(intent);
                }
            }
        });
    }
    void showToashMessege(String messege){
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

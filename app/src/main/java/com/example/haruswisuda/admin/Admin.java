package com.example.haruswisuda.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haruswisuda.R;

public class Admin extends AppCompatActivity {
    Button tambah, edit, hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tambah = findViewById(R.id.btntambah);
        edit = findViewById(R.id.btnedit);
        hapus = findViewById(R.id.btnhapus);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, GejalaAdmin.class).putExtra("req","tambah"));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, GejalaAdmin.class).putExtra("req","edit"));
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, GejalaAdmin.class).putExtra("req","hapus"));
            }
        });
    }
}
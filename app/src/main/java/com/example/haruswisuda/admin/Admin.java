package com.example.haruswisuda.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haruswisuda.R;

public class Admin extends AppCompatActivity {
    Button tambah, hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tambah = findViewById(R.id.btntambah);
        hapus = findViewById(R.id.btnhapus);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, GejalaAdmin.class).putExtra("req","tambah"));
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
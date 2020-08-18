package com.example.haruswisuda;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListdataActivity extends AppCompatActivity {

    TextView listdata;
    ImageView imageView;
    TextView keterangan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);

        listdata = findViewById(R.id.listdata);
        imageView = findViewById(R.id.imageView);
        keterangan = findViewById(R.id.keterangan);
        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("nama");
        int receivedImage = intent.getIntExtra("image",0);
        String receivedKet =  intent.getStringExtra("des");

        listdata.setText(receivedName);
        imageView.setImageResource(receivedImage);
        keterangan.setText(receivedKet);
        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

package com.example.haruswisuda;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoPenyakit extends AppCompatActivity {
    TextView listdata;
    ImageView imageView;
    TextView keterangan;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_penyakit);
        bundle=getIntent().getExtras();
        int res = getResources().getIdentifier(bundle.getString("image"), "drawable", this.getPackageName());
        listdata = findViewById(R.id.namapenyakitinfo);
        imageView = findViewById(R.id.imageInfo);
        keterangan = findViewById(R.id.keteranganinfo);
        imageView.setImageResource(res);
        listdata.setText(bundle.getString("nama"));
        keterangan.setText(bundle.getString("des"));
        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
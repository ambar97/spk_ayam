package com.example.haruswisuda;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListdataActivity extends AppCompatActivity {

    TextView listdata;
    ImageView imageView;
    TextView keterangan;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        bundle=getIntent().getExtras();
        int res = getResources().getIdentifier(bundle.getString("img"), "drawable", this.getPackageName());
        listdata = findViewById(R.id.listdata);
        imageView = findViewById(R.id.imageView);
        keterangan = findViewById(R.id.keterangan);
        imageView.setImageResource(res);
        listdata.setText(bundle.getString("nama")+" ("+bundle.getString("hasil")+"%)");
        keterangan.setText(bundle.getString("des"));
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

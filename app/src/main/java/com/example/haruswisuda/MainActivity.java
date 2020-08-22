package com.example.haruswisuda;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int clickcount=10;
    TextView name;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        this.getSupportActionBar().setDisplayOptions(actionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        View view = getSupportActionBar().getCustomView();
        name = view.findViewById(R.id.actionbarTitle);
        name.setTypeface(Typeface.DEFAULT);


        ImageView info = (ImageView) findViewById(R.id.tentang);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tentang.class);
                startActivity(intent);
            }
        });
        ImageView infosakits = (ImageView) findViewById(R.id.info);

        infosakits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);
            }
        });
        ImageView bantuaninfo = (ImageView) findViewById(R.id.bantuan);

        bantuaninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bantuan.class);
                startActivity(intent);
            }
        });
        ImageView infodiagnosa = (ImageView) findViewById(R.id.diagnosa);

        infodiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Diagnosa.class);
                startActivity(intent);
            }
        });
    }

    public void exitclikkk(View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.pctr)
                .setTitle(R.string.app_name)
                .setMessage("Kamu yakin ingin keluar?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickcount=clickcount-1;
                if(clickcount<=5)
                {
                    Toast.makeText(getApplicationContext(),"Klik "+clickcount+"x lagi untuk masuk ke Login Pakar", Toast.LENGTH_SHORT).show();
                }
                if (clickcount==0)
                {
                    name.setClickable(false);
                    MenuItem item = menu.findItem(R.id.login);
                    item.setVisible(true);
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idLogin;
        idLogin = item.getItemId();
        if (idLogin == R.id.login) {
            startActivity(new Intent(MainActivity.this,LoginPakar.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

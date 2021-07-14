package com.example.mobilnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class KorisnikActivity extends AppCompatActivity {

    TextView ime;
    TextView korime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korisnik);

        renderuj();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch(item.getItemId()){
            case R.id.pocetnaItem:
                intent = new Intent(this, PocetnaActivity.class);
                startActivity(intent);
                return true;
            case R.id.korpaItem:
                intent = new Intent(this, KorpaActivity.class);
                startActivity(intent);
                return true;
            case R.id.profilItem:
                return true;
            case R.id.odjavaItem:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void renderuj(){
        ime = (TextView) findViewById(R.id.korisnikIme);
        String imeString = getIntent().getExtras().getString("korisnikIme","Greska");
        ime.setText(imeString);

        korime = (TextView) findViewById(R.id.korisnikKorime);
        String korimeString = getIntent().getExtras().getString("korisnikKorime","Greska");
        korime.setText((korimeString));
    }
}
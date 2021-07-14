package com.example.mobilnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ProizvodActivity extends AppCompatActivity {

    TextView naslov, cena, opis;
    ImageView slika;
    EditText kolicina;
    Button uKorpu;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proizvod);

        renderuj();

        preferences = getSharedPreferences("KORPA",MODE_PRIVATE);
        editor = preferences.edit();

        String proizvodString = getIntent().getExtras().getString("proizvod","Greska");
        try {
            JSONObject proizvod = new JSONObject(proizvodString);

            Resources res = getResources();
            String mDrawableName = proizvod.getString("slika");
            int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
            slika.setImageResource(resID);
            naslov.setText(proizvod.getString("naslov"));
            cena.setText(proizvod.getString("cena"));
            opis.setText(proizvod.getString("opis"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                intent = new Intent(this, KorisnikActivity.class);
                startActivity(intent);
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
        naslov = (TextView) findViewById(R.id.naslov);
        cena = (TextView) findViewById(R.id.cena);
        opis = (TextView) findViewById(R.id.opis);
        slika = (ImageView) findViewById(R.id.slika);
        kolicina = (EditText) findViewById(R.id.kolicina);
        uKorpu = (Button) findViewById(R.id.uKorpu);
    }

    public void dodajUKorpu(View view){
        JSONObject proizvod = new JSONObject();
        String naslovString = naslov.getText().toString();
        String cenaString = cena.getText().toString();
        String kolicinaString = kolicina.getText().toString();

        try {
            proizvod.put("naslov", naslovString);
            proizvod.put("cena", cenaString);
            proizvod.put("kolicina", kolicinaString);

            String proizvodString = proizvod.toString();

            editor.putString(naslovString, proizvodString);
            editor.commit();

            Toast.makeText(this, "Uspesno dodavanje u korpu!" , Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
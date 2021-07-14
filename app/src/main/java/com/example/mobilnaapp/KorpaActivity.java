package com.example.mobilnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

public class KorpaActivity extends AppCompatActivity {

    TableLayout tabela;
    TextView cenaSvega;
    Button naruci, obrisi;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int ukupnaCena = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korpa);

        renderuj();

        preferences = getSharedPreferences("KORPA",MODE_PRIVATE);
        editor = preferences.edit();

        prikaziKorpu();
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
        tabela = (TableLayout)findViewById(R.id.tabelaKorpa);
        naruci = (Button) findViewById(R.id.naruci);
        obrisi = (Button) findViewById(R.id.obrisi);
        cenaSvega = (TextView) findViewById(R.id.cenaSvega);
    }

    public void prikaziKorpu(){
        tabela.setStretchAllColumns(true);
        tabela.bringToFront();

        TableRow tr =  new TableRow(this);
        TextView txtGeneric = new TextView(this);
        txtGeneric.setText("Proizvod");
        txtGeneric.setTextColor(Color.BLACK);
        txtGeneric.setTextSize(20);
        tr.addView(txtGeneric);

        txtGeneric = new TextView(this);
        txtGeneric.setText("Količina");
        txtGeneric.setTextColor(Color.BLACK);
        txtGeneric.setTextSize(20);
        tr.addView(txtGeneric);

        txtGeneric = new TextView(this);
        txtGeneric.setText("Cena");
        txtGeneric.setTextColor(Color.BLACK);
        txtGeneric.setTextSize(20);
        tr.addView(txtGeneric);

        tabela.addView(tr);

        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            try {
                JSONObject proizvod = new JSONObject(entry.getValue().toString());

                tr = new TableRow(this);
                txtGeneric = new TextView(this);
                txtGeneric.setTextSize(18);
                txtGeneric.setText(proizvod.getString("naslov"));
                tr.addView(txtGeneric);

                txtGeneric = new TextView(this);
                txtGeneric.setTextSize(18);
                txtGeneric.setText(proizvod.getString("kolicina"));
                tr.addView(txtGeneric);

                txtGeneric = new TextView(this);
                txtGeneric.setTextSize(18);

                int ukupnaCenaProizvoda = Integer.parseInt(proizvod.getString("kolicina")) * Integer.parseInt(proizvod.getString("cena"));
                txtGeneric.setText(String.valueOf(ukupnaCenaProizvoda));
                tr.addView(txtGeneric);

                tabela.addView(tr);
                ukupnaCena += ukupnaCenaProizvoda;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cenaSvega.setText(String.valueOf(ukupnaCena));
    }

    public void izvrsiPorudzbinu(View view){
        editor.clear();
        editor.commit();
        tabela.removeAllViews();
        prikaziKorpu();
        Toast.makeText(this, "Uspešna porudžbina!" , Toast.LENGTH_SHORT).show();
    }

    public void isprazniKorpu(View view){
        editor.clear();
        editor.commit();
        tabela.removeAllViews();
        prikaziKorpu();
        Toast.makeText(this, "Sadržaj korpe obrisan!" , Toast.LENGTH_SHORT).show();
    }
}
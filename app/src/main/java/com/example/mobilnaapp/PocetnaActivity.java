package com.example.mobilnaapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class PocetnaActivity extends AppCompatActivity {

    Button detaljnije1, detaljnije2, detaljnije3, detaljnije4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

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
        detaljnije1 = (Button) findViewById(R.id.detaljnije1);
        detaljnije2 = (Button) findViewById(R.id.detaljnije2);
        detaljnije3 = (Button) findViewById(R.id.detaljnije3);
        detaljnije4 = (Button) findViewById(R.id.detaljnije4);
    }

    public void vidiProizvod(View view, int id) {
        String jsonProizvod = null;
        switch(id){
            case 1:
                jsonProizvod = "{\"naslov\":\"Med 1\"," +
                        "\"opis\":\"Opis med1\"," +
                        "\"cena\":300,\"slika\":\"med1cisto\"}";
                break;
            case 2:
                jsonProizvod = "{\"naslov\":\"Med 2\"," +
                        "\"opis\":\"Opis med2\"," +
                        "\"cena\":300,\"slika\":\"med2cisto\"}";
                break;
            case 3:
                jsonProizvod = "{\"naslov\":\"Med 3\"," +
                        "\"opis\":\"Opis med3\"," +
                        "\"cena\":350,\"slika\":\"med3cisto\"}";
                break;
            case 4:
                jsonProizvod = "{\"naslov\":\"Med 4\"," +
                        "\"opis\":\"Opis med4\"," +
                        "\"cena\":400,\"slika\":\"med1cisto\"}";
                break;
        }

        Intent intent = new Intent(this, ProizvodActivity.class);
        intent.putExtra("proizvod", jsonProizvod);

        startActivity(intent);
    }

    public void vidiProizvod1(View view){
        String jsonProizvod = "{\"naslov\":\"Med 1\"," +
                "\"opis\":\"Opis med1\"," +
                "\"cena\":300,\"slika\":\"med1cisto\"}";

        Intent intent = new Intent(this, ProizvodActivity.class);
        intent.putExtra("proizvod", jsonProizvod);

        startActivity(intent);
    }

    public void vidiProizvod2(View view){
        String jsonProizvod = "{\"naslov\":\"Med 2\"," +
                "\"opis\":\"Opis med2\"," +
                "\"cena\":300,\"slika\":\"med2cisto\"}";

        Intent intent = new Intent(this, ProizvodActivity.class);
        intent.putExtra("proizvod", jsonProizvod);

        startActivity(intent);
    }
    public void vidiProizvod3(View view){
        String jsonProizvod = "{\"naslov\":\"Med 3\"," +
                "\"opis\":\"Opis med3\"," +
                "\"cena\":350,\"slika\":\"med3cisto\"}";

        Intent intent = new Intent(this, ProizvodActivity.class);
        intent.putExtra("proizvod", jsonProizvod);

        startActivity(intent);
    }

    public void vidiProizvod4(View view){
        String jsonProizvod = "{\"naslov\":\"Med 4\"," +
                "\"opis\":\"Opis med4\"," +
                "\"cena\":400,\"slika\":\"med1cisto\"}";

        Intent intent = new Intent(this, ProizvodActivity.class);
        intent.putExtra("proizvod", jsonProizvod);

        startActivity(intent);
    }
}
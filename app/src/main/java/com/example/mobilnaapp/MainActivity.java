package com.example.mobilnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText Korime, Lozinka;
    Button login;

    JSONObject korisnik;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderuj();

        preferences = getSharedPreferences("USER", MODE_PRIVATE);
        editor = preferences.edit();

        korisnik = new JSONObject();
        try {
            korisnik.put("ime", "Petar Petrovic");
            korisnik.put("korime", "petar");
            korisnik.put("lozinka", "petar123");

            editor.putString("trenutniKorisnik", korisnik.toString());
            editor.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(korisnik.getString("korime").equals(Korime.getText().toString()) && korisnik.getString("lozinka").equals(Lozinka.getText().toString())){
                        Intent intent = new Intent(v.getContext(), KorisnikActivity.class);
                        intent.putExtra("korisnikIme", korisnik.getString("ime"));
                        intent.putExtra("korisnikKorime", korisnik.getString("korime"));
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(v.getContext(), "Pogrešna lozinka!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(v.getContext(), "Greška!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public void renderuj(){
        Korime = findViewById(R.id.loginKorime);
        Lozinka = findViewById(R.id.loginLozinka);
        login = (Button)findViewById(R.id.loginDugme);
    }
}
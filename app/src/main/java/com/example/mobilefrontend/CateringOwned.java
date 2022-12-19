package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.RestrictionEntry;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CateringOwned extends AppCompatActivity {
    String Adres;
    String Zamowienia;

    JSONObject obj;


    ArrayList<String> listaKcal = new ArrayList<>();
    ArrayList<String> listaRemarks = new ArrayList<>();
    ArrayList<String> listaTwon = new ArrayList<>();
    ArrayList<String> listaKodowPocztowych = new ArrayList<>();
    ArrayList<String> listaStreet = new ArrayList<>();
    ArrayList<String> listaDataOd = new ArrayList<>();
    ArrayList<String> listaDataDo = new ArrayList<>();
    ArrayList<String> listaCena = new ArrayList<>();

    TextView textViewblad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_owned);


        listaKcal = new ArrayList<>();
        listaRemarks = new ArrayList<>();
        listaTwon = new ArrayList<>();
        listaKodowPocztowych = new ArrayList<>();
        listaStreet = new ArrayList<>();
        listaDataOd = new ArrayList<>();
        listaDataDo = new ArrayList<>();
        listaCena = new ArrayList<>();
        textViewblad = (TextView) findViewById(R.id.textView7);

        try {
            PobierzKatering();

        } catch (Exception e) {
            Toast.makeText(CateringOwned.this, "Nie działa" + e, Toast.LENGTH_SHORT).show();
        }


    }


    void PobierzKatering() {
        String urlAdres = Data.url + Data.UrlGetKateringZamowione  + 1;

        RequestQueue queue = Volley.newRequestQueue(CateringOwned.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAdres, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Zamowienia = response;
                Zamowienia = "{\"Kateringi\":" + Zamowienia + "}";

                try {
                    obj = new JSONObject(Zamowienia);

                } catch (JSONException e) {

                    e.printStackTrace();
                }

                try {

                    JSONArray jsonArray = obj.getJSONArray("Kateringi");
                    for (int i = 0; i < jsonArray.length(); i++) {



                        JSONObject AdresyJSON = jsonArray.getJSONObject(i);
                        listaDataOd.add(AdresyJSON.getString("dataOd"));
                        listaDataDo.add(AdresyJSON.getString("dataDo"));
                        listaCena.add(AdresyJSON.getString("cena"));
                        listaTwon.add(AdresyJSON.getString("miasto"));
                        listaKodowPocztowych.add(AdresyJSON.getString("kodPocztowy"));
                        listaStreet.add(AdresyJSON.getString("ulica"));
                        listaKcal.add(AdresyJSON.getString("kcal"));
                        listaRemarks.add(AdresyJSON.getString("uwagi"));


                    }
                } catch (JSONException e) {
                    Toast.makeText(CateringOwned.this, "Nie działa" + e, Toast.LENGTH_SHORT).show();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CateringOwned.this, "Nie działa", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

    }


}
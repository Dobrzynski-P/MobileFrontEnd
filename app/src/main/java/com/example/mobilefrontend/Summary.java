package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        TextView textViewCatering = (TextView) findViewById(R.id.textViewSummaryCatering);
        TextView textViewAddress = (TextView) findViewById(R.id.textViewSummaryAddress);
        TextView textViewPrice = (TextView) findViewById(R.id.textViewSummaryPrice);

        textViewCatering.setText(""+Data.name+", kcal: "+Data.kcal+", ilość dni: "+Data.days);

        textViewAddress.setText(""+ Data.street + ", "+ Data.city +", "+Data.postalCode + ". Uwagi: "+Data.remarks);

        textViewPrice.setText(""+Data.price);

        String url = "http://10.0.2.2:8000/"+Data.UrlPostZamowienie +Data.KateringId+"/"+  Data.days + "/" + Data.city + "/" + Data.postalCode + "/" + Data.street + "/" + Data.kcal + "/" + Data.remarks + "/"+Data.UID;//Dodaj do Klasy Dane
        Button buttonZamowienie=(Button) findViewById(R.id.buttonSummaryPermit);

        System.out.println(url);

        buttonZamowienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://10.0.2.2:8000/"+Data.UrlPostZamowienie +Data.KateringId+"/"+  Data.days + "/" + Data.city + "/" + Data.postalCode + "/" + Data.street + "/" + Data.kcal + "/" + Data.remarks + "/"+Data.UID;//Dodaj do Klasy Dane
                RequestQueue queue = Volley.newRequestQueue(Summary.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Summary.this, "POSZŁO", Toast.LENGTH_SHORT).show();
                        System.out.println(url);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Summary.this, "Nie działa", Toast.LENGTH_SHORT).show();
                        System.out.println("N3"+error);
                    }
                });
                queue.add(stringRequest);
            }
        });


/*        buttonZamowienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // http://127.0.0.1:8000/Zamowienie/100/Gostynin/09-500/Zacisze/2000/Zostawic%20pod%20drzwiami/1
                String url= "http://10.0.2.2:8000/zamowione/"+Data.days+"/"+Data.city+"/"+Data.postalCode+"/"+Data.street+"/"+Data.kcal+"/"+Data.remarks+"/1";//Dodaj do Klasy Dane

                StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
                        response -> Toast.makeText(Summary.this,"Sukces",Toast.LENGTH_SHORT).show(),
                        error -> Toast.makeText(Summary.this,"Blad"+error,Toast.LENGTH_SHORT).show()){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        try{
                            //params.put("idKateringu", Data.dataOd.toString());   //do resta, funkcja sprawdzająca aktualną date i dodanie 1 dzień, zapisywana jako data w bazie
                            //params.put("dniKateringu", "7"/*Data.days.toString());   //do resta, wysłać okres a nie date, zapisywana jako data
                            params.put("cena", "40"*//*Data.price.toString()*//*);      //do resta
                            params.put("miasto", "Gostynin"*//*Data.town.toString()*//*);
                            params.put("kodPocztowy", "55555"*//*Data.postalCode.toString()*//*);
                            params.put("ulica", "Zacisze"*//*Data.street.toString()*//*);
                            params.put("kcal", "1500"*//*Data.kcal.toString()*//*);       //1000 1500 2000
                            params.put("uwagi", "jest super"*//*Data.remarks.toString()*//*);
                            params.put("Klienci_idKlienci", "1555"*//*Data.remarks.toString()*//*);
                            //return params;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return params;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Summary.this);
                queue.add(stringRequest);

                Intent screen = new Intent(getApplicationContext(), Congratulations.class);
                startActivity(screen);
            }
        });*/
    }
}
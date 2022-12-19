package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.Arrays;
import java.util.SortedMap;

public class Orders extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String Dane;
    JSONObject obj;
    TextView textViewOrdersPrice, textViewDescription;
    Spinner spinnerOrdersCateringName, spinnerOrdersKcal, spinnerOrdersDayCount;
    ArrayList<String> listanazw, listacen, listaopisow;
    ArrayAdapter<CharSequence> adapterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        textViewOrdersPrice = (TextView) findViewById(R.id.textViewOrdersPrice);
        textViewDescription = (TextView) findViewById(R.id.textViewOrdersDescription);
        spinnerOrdersKcal = (Spinner) findViewById(R.id.spinnerOrdersKcal);
        spinnerOrdersDayCount = (Spinner) findViewById(R.id.spinnerOrdersDayCount);
        spinnerOrdersCateringName = (Spinner) findViewById(R.id.spinnerOrdersCateringName);
        listanazw = new ArrayList<String>();
        listacen = new ArrayList<>();
        listaopisow = new ArrayList<>();

        ArrayAdapter<CharSequence> adapterKcal = ArrayAdapter.createFromResource(this, R.array.Kcal, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterDays = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        adapterKcal.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOrdersKcal.setAdapter(adapterKcal);
        spinnerOrdersDayCount.setAdapter(adapterDays);
        spinnerOrdersKcal.setOnItemSelectedListener(this);
        spinnerOrdersDayCount.setOnItemSelectedListener(this);

        String url = "http://10.0.2.2:8000/"+Data.UrlGetKateringi;//Dodaj do Klasy Dane
        RequestQueue queue = Volley.newRequestQueue(Orders.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Dane = response;
                Dane = "{\"Kateringi\":" + Dane + "}";

                try {
                    obj = new JSONObject(Dane);

                } catch (JSONException e) {

                    System.out.println("N1" + e);

                    e.printStackTrace();
                }

                try {

                    JSONArray jsonArray = obj.getJSONArray("Kateringi");
                    ArrayList<String> ar = new ArrayList<String>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject katering = jsonArray.getJSONObject(i);
                        listanazw.add(i, katering.getString("nazwa"));
                        listacen.add(i, katering.getString("cenaPodstawowa"));
                        listaopisow.add(i, katering.getString("opis"));

                        ar.add(i, katering.getString("nazwa"));

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Orders.this,
                            android.R.layout.simple_spinner_item, ar);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    spinnerOrdersCateringName.setAdapter(adapter);

                } catch (JSONException e) {
                    System.out.println("N2 " + e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Orders.this, "Nie działa", Toast.LENGTH_SHORT).show();

                System.out.println("N3");
            }
        });
        queue.add(stringRequest);




        Button buttonOrder = (Button) findViewById(R.id.buttonOrdersOrder);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.price = textViewOrdersPrice.getText().toString();
                Data.name = spinnerOrdersCateringName.getSelectedItem().toString();
                Data.kcal = spinnerOrdersKcal.getSelectedItem().toString();
                Data.days = spinnerOrdersDayCount.getSelectedItem().toString();
                Intent screen = new Intent(getApplicationContext(), Order_Address.class);
                startActivity(screen);
            }
        });


        spinnerOrdersDayCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Data.days=spinnerOrdersDayCount.getSelectedItem().toString();
                GetPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textViewOrdersPrice.setText("");
            }
        });


        spinnerOrdersCateringName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textViewDescription.setText(listaopisow.get(spinnerOrdersCateringName.getSelectedItemPosition()));
                Data.KateringId=spinnerOrdersCateringName.getSelectedItemPosition();
                GetPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textViewOrdersPrice.setText("");
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Data.kcal=spinnerOrdersKcal.getSelectedItem().toString();
        GetPrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        textViewOrdersPrice.setText("");
    }
   void GetPrice() {

       String url = "http://10.0.2.2:8000/"+Data.UrlGetCena + Data.KateringId + "/" + Data.days + "/" + Data.kcal;//Dodaj do Klasy Dane

       RequestQueue queue = Volley.newRequestQueue(Orders.this);

       StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               textViewOrdersPrice.setText(response +" zł");
               Data.price=response.toString();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(Orders.this, "Nie działa", Toast.LENGTH_SHORT).show();


               System.out.println("N3");
           }
       });
       queue.add(stringRequest);
   }

}
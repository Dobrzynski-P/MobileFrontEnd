package com.example.mobilefrontend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MyAccount extends AppCompatActivity {
    public EditText EPesronname,Etelephone,Eemail,Eadres,Epay;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        EPesronname = findViewById(R.id.editTextMyAccountName);
        Etelephone = findViewById(R.id.editTextMyAccountPhoneNumber);
        Eemail = findViewById(R.id.editTextMyAccountEMail);
        Eadres = findViewById(R.id.editTextMyAccountShipmentAddress);
        //Epay = findViewById(R.id.editTextMyAccountPayment);
        Button buttonMyAccountSave = (Button) findViewById(R.id.buttonMyAccountSave);

    }
            public void onClick(View view) {

                String url="http://192.168.189.165/index.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        response -> System.out.println("Succes"),
                        error ->System.out.println("Error!: "+error)){
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError{
                        Map<String,String> params = new HashMap<>();
                        params.put("Imię ",EPesronname.getText().toString());
                        params.put("Telefon ",Etelephone.getText().toString());
                        params.put("E-mail ",Eemail.getText().toString());
                        params.put("Adres ",Eadres.getText().toString());
                        params.put("Metoda płatnośći ",Epay.getText().toString());
                        return params;

                }
                };
                requestQueue = Volley.newRequestQueue(MyAccount.this);
                requestQueue.add(stringRequest);
                };

    }
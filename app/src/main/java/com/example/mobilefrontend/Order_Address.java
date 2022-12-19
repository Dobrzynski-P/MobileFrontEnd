package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Order_Address extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_address);

        EditText editTextStreet = (EditText) findViewById(R.id.editTextOrderAddressStreet);
        EditText editTextPostalCode = (EditText) findViewById(R.id.editTextOrderAddressCity);
        EditText editTextRegion = (EditText) findViewById(R.id.editTextOrderAddressPostalCode);
        EditText editTextRemarks = (EditText) findViewById(R.id.editTextOrderAddressRemarks);

        Button buttonGoToSummary = (Button) findViewById(R.id.buttonOrderAddressGoToSummary);

        buttonGoToSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.street = editTextStreet.getText().toString();
                Data.city = editTextPostalCode.getText().toString();
                Data.postalCode = editTextRegion.getText().toString();
                Data.remarks = editTextRemarks.getText().toString();
                Intent screen = new Intent(getApplicationContext(), Summary.class);
                startActivity(screen);
            }
        });


    }

}
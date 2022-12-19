package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Congratulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);


        Button buttonZamowienie=(Button) findViewById(R.id.buttonCongratulationsBack);

        buttonZamowienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(getApplicationContext(),Menu.class);
                startActivity(screen);
            }
        });
    }
}
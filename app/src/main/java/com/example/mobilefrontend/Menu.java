package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonOtworzPopup = (Button) findViewById(R.id.buttonMenuOpenPopup);
        Button buttonZamowienie = (Button) findViewById(R.id.buttonMenuOrderExample);
        Toast.makeText(Menu.this, "UID + = "+Data.UID, Toast.LENGTH_SHORT).show();
        //Zrobić ogólną funkcję która będzie przenosiła do zamówienia przesyłając do niej informacje z rodzajem zamówienia
        //nie robić nowych funkcji przycisków do każdego zamówienia
        buttonZamowienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(getApplicationContext(), Orders.class);
                startActivity(screen);
            }
        });

        buttonOtworzPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtworzPopup();
            }
        });

    }
    public void OtworzPopup(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_menu, null);
        dialogBuilder.setView(contactPopupView);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Button buttonMojeKonto = (Button) contactPopupView.findViewById(R.id.buttonPopUpMenuMyAccount);
        Button buttonMojeKateringi = (Button) contactPopupView.findViewById(R.id.buttonPopUpMenuMyCatering);
        Button buttonKontakt = (Button) contactPopupView.findViewById(R.id.buttonPopUpMenuContact);

        buttonMojeKateringi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(getApplicationContext(), CateringOwned.class);
                startActivity(screen);
            }
        });

        buttonMojeKonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(getApplicationContext(), MyAccount.class);
                startActivity(screen);
            }
        });

        buttonKontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(getApplicationContext(), Contact.class);
                startActivity(screen);
            }
        });

    }
}
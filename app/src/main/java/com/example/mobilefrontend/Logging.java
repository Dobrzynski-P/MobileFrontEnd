package com.example.mobilefrontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logging extends AppCompatActivity {

    Button buttonZalogujSie;
    Button buttonZarejestrujSie;
    EditText editTextLoggingEmail,editTextLoggingPassword;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
       // Intent mainFunctionality = new Intent(Logging.this, Menu.class);
       // startActivity(mainFunctionality);
        buttonZarejestrujSie = (Button)findViewById(R.id.buttonLoggingRegister);
        editTextLoggingEmail= (EditText) findViewById(R.id.editTextLoggingNick);
        editTextLoggingPassword= (EditText) findViewById(R.id.editTextLoggingPassword);
        buttonZalogujSie = (Button)findViewById(R.id.buttonLoggingLogIn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        buttonZalogujSie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = editTextLoggingEmail.getText().toString().trim();
                String password = editTextLoggingPassword.getText().toString().trim();

                if (mail.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Brak emailu lub hasła", Toast.LENGTH_SHORT).show();
                } else {
                    // Logujemy
                    firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                checkmailverfication();
                            } else {
                                Toast.makeText(getApplicationContext(), "Konto nie istnieje", Toast.LENGTH_SHORT).show(); // lub brak internetu
                            }
                        }
                    });
                }
            }
        });
        buttonZarejestrujSie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(getApplicationContext(), Registration.class);
                startActivity(screen);

            }
        });
    }
    private void checkmailverfication()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Data.UID=firebaseUser.getUid();
        finish();
        Intent mainFunctionality = new Intent(Logging.this, Menu.class);
        mainFunctionality.putExtra("userID", firebaseUser.getUid());
        startActivity(mainFunctionality);
    }
    }

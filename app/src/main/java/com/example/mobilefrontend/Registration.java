package com.example.mobilefrontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Registration extends AppCompatActivity {

    EditText editTextNewEmail, editTextNewPassword, editTextRepeatPassword;
    Button buttonRegistrationRegister;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonRegistrationRegister = (Button)findViewById(R.id.buttonRegistrationRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        editTextNewEmail = (EditText) findViewById(R.id.editTextRegistrationEMail);
        editTextNewPassword = (EditText) findViewById(R.id.editTextRegistrationPassword);
        editTextRepeatPassword = (EditText) findViewById(R.id.editTextRegistrationRepeatPassword);
        buttonRegistrationRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountFire();
            }
            private void ToDataBaseAccount()
            {
                String url = "http://10.0.2.2:8000/"+Data.UrlPostKlient+Data.UID+"/"+mail;//Dodaj do Klasy Dane
                RequestQueue queue = Volley.newRequestQueue(Registration.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Registration.this, "POSZŁO", Toast.LENGTH_SHORT).show();
                        System.out.println(url);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registration.this, "Nie działa", Toast.LENGTH_SHORT).show();
                        System.out.println("N3"+error);
                    }
                });
                queue.add(stringRequest);
                finish();
                startActivity(new Intent(Registration.this, Logging.class));
            }



            private void CreateAccountFire()
            {
                mail = editTextNewEmail.getText().toString();
                String password = editTextNewPassword.getText().toString();

                if(editTextRepeatPassword.getText().toString().equals(editTextNewPassword.getText().toString())){
                    if (mail.isEmpty() || password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Wszystkie pola są wymagane", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 7) {
                        Toast.makeText(getApplicationContext(), "Hasło musi zawierać conajmniej 7 znaków", Toast.LENGTH_SHORT).show();
                    } else {
                        // Rejestrujemy
                        firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Rejestracja udana", Toast.LENGTH_SHORT).show();
                                    sendEmailVerification();
                                }
                            }
                        });
                    }
                }
                else{Toast.makeText(getApplicationContext(), "Wpisane hasła nie są takie same", Toast.LENGTH_LONG).show();}


            }
            private void sendEmailVerification() {
                firebaseUser = firebaseAuth.getCurrentUser();
                Data.UID = firebaseAuth.getUid();
                ToDataBaseAccount();
                Toast.makeText(getApplicationContext(), "UID = "+Data.UID, Toast.LENGTH_SHORT).show();
                if (firebaseUser != null) {
                    firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firebaseAuth.signOut();
                            finish();
                            startActivity(new Intent(Registration.this, Logging.class));
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Weryfikacja nie powiodła się", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
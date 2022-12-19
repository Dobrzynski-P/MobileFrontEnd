package com.example.mobilefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Contact extends AppCompatActivity {
    InputStream inputStream;

    int i=0;
    String[] ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button ButtonDownload=(Button) findViewById(R.id.buttonTESTPOBRANIE);
   //     inputStream=getResources().openRawResource(R.raw.kateringi);

        ButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));

                  try{
                        String csvLine;
                        while ((csvLine = reader.readLine())!=null) {

                        try {
                            //Data.StringFromCSV[i]=csvLine;
                             Data.StringFromCSVArray.add(csvLine.toString());

                           System.out.println("Dane pobrane z Excela = "+Data.StringFromCSVArray.get(i).toString());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Błąd"+e);
                        }
                        i++;
                        }
                  }
                  catch (Exception e)
                  {
                      System.out.println("Błąd"+e);
                  }
            }

        });

    }
}
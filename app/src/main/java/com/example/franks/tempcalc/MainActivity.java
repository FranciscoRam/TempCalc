package com.example.franks.tempcalc;

import android.content.Intent;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



public class MainActivity extends AppCompatActivity {
    Spinner spinIn;
    Spinner spinOut;
    String[] gradoIn= {"Celcius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinIn = (Spinner)findViewById(R.id.spinnerIn);

        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,gradoIn);
        spinIn.setAdapter(adapt);

        spinOut = (Spinner)findViewById(R.id.spinnerOut);

        spinOut.setAdapter(adapt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_about) {
            //about();
            Intent intent = new Intent(MainActivity.this,Datos.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
/*
    private void about() {
        TempCalcApp app = (TempCalcApp)getApplication();
        String strUrl = app.getAboutUrl();


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
    */
}

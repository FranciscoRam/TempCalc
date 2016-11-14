package com.example.franks.tempcalc;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    Spinner spinIn;
    Spinner spinOut;
    String[] gradoIn= {"Celcius", "Fahrenheit", "Kelvin"};

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    /*
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    */
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_CHANGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

    @OnClick(R.id.btnSubmit)
    public void handleSubmit() {
        hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();

        if(!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);

            //INSERVIBLE VERIFICAR COMO QUITAR
            //int tipPercentage = getTipPrecentage();
            int tipResu = getTipResu();

            double tip = total * (tipResu/100d);

            String strTip = String.format(getString(R.string.global_message_tip), tip);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    public void handleClickIncrease() {
        // Cuando des click a + debe llamar a handleTipChange y sumar 1
    }

    public void handleClickDecrease() {
        // Cuando des click a - debe llamar a handleTipChange y restar 1
    }

    public int getTipResu() {

        return DEFAULT_TIP_CHANGE;
    }


    public void handleTipChange(int change) {
        // 1 Llamar a Get Tip Percentage (en una variable)
        // 2 aplicar el incremento/decremento que viene en la variable change
        // 3 si tipPercentage mayor que 0 entonces colocar el valor del incremento en el input de la vista

    }


    private void hideKeyboard() {
        InputMethodManager inputManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
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

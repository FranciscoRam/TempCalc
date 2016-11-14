package com.example.franks.tempcalc;

import com.example.franks.tempcalc.fragments.TipHistoryListFragment;
import com.example.franks.tempcalc.fragments.TipHistoryListFragmentListener;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;


import android.os.Bundle;
import android.os.StrictMode;
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
import android.widget.Toast;

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

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_CHANGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;

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

            //double tip = total * (tipResu/100d);
            double tip = tipResu;

            String strTip = String.format(getString(R.string.global_message_tip), tip);
            fragmentListener.action(strTip);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease() {
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease() {
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    public int getTipResu() {
        int tipChange = DEFAULT_TIP_CHANGE;

        String strInputTipChange = inputBill.getText().toString().trim();

        if(!strInputTipChange.isEmpty()) {
            tipChange = Integer.parseInt(strInputTipChange);
        }
        else {
            inputBill.setText(String.valueOf(DEFAULT_TIP_CHANGE));
        }


        return tipChange;
    }


    public void handleTipChange(int change) {
        int tipRes = getTipResu();
        tipRes += change;

        if(tipRes > 0) {
            inputBill.setText(String.valueOf(tipRes));
        }
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

package com.example.franks.tempcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.franks.tempcalc.Datos;
import com.example.franks.tempcalc.R;
import com.example.franks.tempcalc.fragments.TipHistoryListFragment;
import com.example.franks.tempcalc.fragments.TipHistoryListFragmentListener;


import com.example.franks.tempcalc.TempCalcApp;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.Date;
import com.example.franks.tempcalc.models.TipRecord;

public class MainActivity extends AppCompatActivity {
    Spinner spinIn;
    Spinner spinOut;
    String[] gradoIn= {"Celsius", "Fahrenheit", "Kelvin"};

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
            //int tipResu = getTipResu();
            double tipResu = 1;
            //double tip = total * (tipResu/100d);
            TipRecord record = new TipRecord();

            record.setBill(total);


            String IN= spinIn.getItemAtPosition(spinIn.getSelectedItemPosition()).toString();
            String OUT= spinOut.getItemAtPosition(spinOut.getSelectedItemPosition()).toString();


            if(IN.equals("Celsius")&&OUT.equals("Fahrenheit")){ //C a F
                tipResu=total*1.8+32;}

            if(IN.equals("Celsius")&&OUT.equals("Kelvin")){ //C a K
                tipResu=total+273.15;}

            if(IN.equals("Fahrenheit")&&OUT.equals("Celsius")){ //F a C
                tipResu=(total-32)/1.8;}

            if(IN.equals("Fahrenheit")&&OUT.equals("Kelvin")){ //F a K
                tipResu=((total-32)/1.8)+273.15;}

            if(IN.equals("Kelvin")&&OUT.equals("Celsius")){ //K a C
                tipResu=total-273.15;}

            if(IN.equals("Kelvin")&&OUT.equals("Fahrenheit")){ //K a F
                tipResu=1.8*(total-273.15)+32;}

            if (IN==OUT){ //Mismas Unidades
               tipResu=total;
            }

            record.setTipPercentage(tipResu);

            record.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip), record.getTip());
            fragmentListener.addToList(record);

            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }


    @OnClick(R.id.btnClear)
    public void handleClickClear() {
        fragmentListener.clearList();
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

    //MODIFICAR ESTA PARTE PARA LAS TEMPERATURAS
/*

*/
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

package com.example.franks.tempcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.franks.tempcalc.R;


public class activity_detail extends AppCompatActivity{
    @Bind(R.id.txtTotal)
    TextView txtTotal;
    @Bind(R.id.txtPropina)
    TextView txtPropina;
    @Bind(R.id.txtFecha)
    TextView txtFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);



        Intent intent=getIntent();


        txtTotal.setText(intent.getStringExtra("Total"));
        txtPropina.setText(intent.getStringExtra("Propina"));
        txtFecha.setText(intent.getStringExtra("Fecha"));

    }
}

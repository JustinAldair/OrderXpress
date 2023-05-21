package com.example.orderxpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    private TextView rediRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        rediRegistrate = (TextView) findViewById(R.id.rediRegistrate);


    }
    //Transicion a interfaz de Regristrar cuenta
    public void cambiarRegistro(View view) {
        Intent cmRegistro = new Intent(getApplicationContext(),registrarCuenta.class);
        startActivity(cmRegistro);
        finish();
    }
}

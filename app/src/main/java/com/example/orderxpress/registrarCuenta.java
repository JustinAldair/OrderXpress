package com.example.orderxpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class registrarCuenta extends AppCompatActivity {
private TextView rediIniciarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);

        rediIniciarSesion=(TextView) findViewById(R.id.rdLogin);
    }

    //Transicion a interfaz de Login
    public void cambiarLogin(View view) {
        Intent cmLogin = new Intent(getApplicationContext(),Inicio.class);
        startActivity(cmLogin);
        finish();
    }
}
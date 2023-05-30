package com.example.orderxpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class soliRecuperarCuenta extends AppCompatActivity {
private EditText correo;
private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soli_recuperar_cuenta);
        correo=(EditText) findViewById(R.id.etSRCCorreo);

        enviar = findViewById(R.id.btnSoliRecuperar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarUsuario();
            }
        });
    }
    private void registrarUsuario() {
        String email = correo.getText().toString().trim();

        // Realiza las validaciones necesarias
        if (email.isEmpty()) {
            correo.setError("Ingrese un correo");
            correo.requestFocus();
            return;
        } else {
            Intent cmRecuCuenta = new Intent(getApplicationContext(),recuperarCuenta.class);
            startActivity(cmRecuCuenta);
            finish();
        }

    }
    public void cambiarLogin(View view) {
        Intent cmLogin = new Intent(getApplicationContext(),Inicio.class);
        startActivity(cmLogin);
        finish();
    }
}
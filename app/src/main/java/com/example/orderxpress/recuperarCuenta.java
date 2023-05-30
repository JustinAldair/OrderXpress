package com.example.orderxpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class recuperarCuenta extends AppCompatActivity {
private Button confirmar;
private EditText psw1, psw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);

        confirmar= (Button) findViewById(R.id.btnRecuperarCuenta);
        psw1=(EditText)findViewById(R.id.etRCPassword);
        psw2=(EditText)findViewById(R.id.etRCConfirmarPassword);

        confirmar = findViewById(R.id.btnRecuperarCuenta);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recuperarCuenta();
            }
        });
    }
    private void recuperarCuenta() {
        String contra = psw1.getText().toString().trim();
        String confirContra = psw2.getText().toString().trim();

        // Realiza las validaciones necesarias
        if (contra.isEmpty()) {
            psw1.setError("Ingrese un nuevo password");
            psw1.requestFocus();
            return;
        }
        if (confirContra.isEmpty()) {
            psw2.setError("Ingrese de nuevo el password");
            psw2.requestFocus();
            return;
        }
        if (!contra.equals(confirContra)) {
            psw2.setError("Las contraseñas no coinciden");
            psw2.requestFocus();
            return;
        }else {
            Intent cmLogin = new Intent(getApplicationContext(),Inicio.class);
            startActivity(cmLogin);
            finish();
        }

    }

}
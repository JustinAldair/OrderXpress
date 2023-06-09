package com.example.orderxpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    private TextView rediRegistrate;
    private EditText usuario,password;

    private Button btnIniciarSesion;
    private Button button;
    private EditText edtLpassword;
    private ImageView passwordIcon;
    private boolean passwordVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        TextView textView = findViewById(R.id.rediRegistrate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(Inicio.this, registrarCuenta.class);
                startActivity(intent);
                finish();
            }
        });
        TextView textView2 = findViewById(R.id.recuperarPassword);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(Inicio.this, recuperarCuenta.class);
                startActivity(intent);
                finish();
            }
        });


        usuario=(EditText) findViewById(R.id.etIUsuario);
        password=(EditText) findViewById(R.id.etIPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        edtLpassword = findViewById(R.id.etIPassword);
        passwordIcon = findViewById(R.id.passwordicon);
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordVisible = !passwordVisible;
                updatePasswordVisibility();
            }
        });



        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) view.getContext();
                Intent intent = new Intent(Inicio.this, MainActivityMenu.class);
                startActivity(intent);
            }
        });

    }
    //Transicion a interfaz de Regristrar cuenta

    public void cambiarRegistro(View view) {
        Intent cmRegistro = new Intent(getApplicationContext(),registrarCuenta.class);
        startActivity(cmRegistro);
        finish();
    }



    public void recuperarContra(View view) {
        Intent cmSoliContra = new Intent(getApplicationContext(),soliRecuperarCuenta.class);
        startActivity(cmSoliContra);
        finish();
    }

    private void updatePasswordVisibility() {
        if (passwordVisible) {
            passwordIcon.setImageResource(R.drawable.eye);
            edtLpassword.setTransformationMethod(null); // Mostrar contraseña
        } else {
            passwordIcon.setImageResource(R.drawable.eyeoff);
            edtLpassword.setTransformationMethod(new PasswordTransformationMethod()); // Ocultar contraseña
        }

        // Mover el cursor al final del texto
        edtLpassword.setSelection(edtLpassword.getText().length());
    }



}

package com.example.orderxpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    private TextView rediRegistrate;
    private EditText usuario,password;

    private Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        rediRegistrate = (TextView) findViewById(R.id.rediRegistrate);

        usuario=(EditText) findViewById(R.id.etIUsuario);
        password=(EditText) findViewById(R.id.etIPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);


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

    //--private void iniciarSesion(){
     //   String username = usuario.getText().toString().trim();
       // String psw = password.getText().toString().trim();

        // Realiza las validaciones necesarias
        //if (username.isEmpty()) {
          //  usuario.setError("Ingrese un nombre de usuario");
           // usuario.requestFocus();
    //return;
      //  }else {
        //    if (psw.isEmpty()) {
          //      password.setError("Ingrese una contraseña");
            //    password.requestFocus();
              //  return;
           // } else {
                // Validaciones exitosas, se puede iniciar sesion

            ///}
        //}
    //}


}

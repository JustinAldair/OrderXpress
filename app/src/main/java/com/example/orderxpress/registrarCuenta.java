package com.example.orderxpress;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registrarCuenta extends AppCompatActivity {
private TextView rediIniciarSesion;
private Button btnRegistrar;
private CheckBox opc1,opc2;
private EditText nombre,usuario,password,correo,telefono;
    private EditText edtLpassword;
    private ImageView passwordIcon;
    private boolean passwordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);

        rediIniciarSesion=(TextView) findViewById(R.id.rdLogin);

        nombre=(EditText) findViewById(R.id.etNombre);
        usuario=(EditText) findViewById(R.id.etIUsuario);
        password=(EditText) findViewById(R.id.etIPassword);
        correo=(EditText) findViewById(R.id.etSRCCorreo);
        telefono=(EditText) findViewById(R.id.etNumero);

        opc1= (CheckBox) findViewById(R.id.cbRepartidor);
        opc2= (CheckBox) findViewById(R.id.cbPedidos);




        edtLpassword = findViewById(R.id.etIPassword);
        passwordIcon = findViewById(R.id.passwordicon);
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(edtLpassword, passwordIcon);
            }
        });

        opc1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    opc2.setChecked(false); // Desmarca opc2 si opc1 está seleccionada
                    opc2.setEnabled(false); // Deshabilita opc2
                } else {
                    opc2.setEnabled(true); // Habilita opc2 cuando opc1 no está seleccionada
                }
            }
        });

        opc2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    opc1.setChecked(false); // Desmarca opc1 si opc2 está seleccionada
                    opc1.setEnabled(false); // Deshabilita opc1
                } else {
                    opc1.setEnabled(true); // Habilita opc1 cuando opc2 no está seleccionada
                }
            }
        });

        // Deseleccionar ambas opciones CheckBox al iniciar esta interfaz
        opc1.setChecked(false);
        opc2.setChecked(false);

        Button btnRegistrar = findViewById(R.id.btnRegistrarse);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarUsuario();
            }
        });
    }

    //Transicion a interfaz de Login
    public void cambiarLogin(View view) {
        Intent cmLogin = new Intent(getApplicationContext(),Inicio.class);
        startActivity(cmLogin);
        finish();
    }

    private void registrarUsuario() {
        String name = nombre.getText().toString().trim();
        String username = usuario.getText().toString().trim();
        String psw = password.getText().toString().trim();
        String email = correo.getText().toString().trim();
        String phone = telefono.getText().toString().trim();

        // Realiza las validaciones necesarias
        if (name.isEmpty()) {
            nombre.setError("Ingrese un nombre de usuario");
            nombre.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            usuario.setError("Ingrese un nombre de usuario");
            usuario.requestFocus();
            return;
        }
        if (psw.isEmpty()) {
            password.setError("Ingrese una contraseña");
            password.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            correo.setError("Ingrese un correo electronico");
            correo.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            telefono.setError("Ingrese un número telefonico");
            telefono.requestFocus();
            return;
        }

        String numeroTelefono = telefono.getText().toString().trim();
        if (numeroTelefono.length() != 10) {
            // Longitud del número de teléfono incorrecta, muestra un mensaje de error
            Toast.makeText(registrarCuenta.this, "El número de Teléfono debe tener 10 caracteres", Toast.LENGTH_SHORT).show();
        } else if (!opc1.isChecked() && !opc2.isChecked()) {
            // Ningun tipo de usuario, muestra un mensaje de error
            Toast.makeText(registrarCuenta.this, "Debes seleccionar un Tipo de Usuario", Toast.LENGTH_SHORT).show();
        } else {
            // Validaciones exitosas, se pueden guardar los datos

        }

    }

    private void togglePasswordVisibility(EditText editText, ImageView icon) {
        int inputType = editText.getInputType();
        if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            icon.setImageResource(R.drawable.eyeoff);
        } else {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            icon.setImageResource(R.drawable.eye);
        }
        editText.setSelection(editText.getText().length());
    }

}
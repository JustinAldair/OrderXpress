package com.example.orderxpress.Herramientas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.orderxpress.Fragment.HomeFragment;
import com.example.orderxpress.Herramientas.Calculadora.CalculadoraFragment;
import com.example.orderxpress.Herramientas.Conversor.ConversorFragment;
import com.example.orderxpress.Herramientas.Notas.NotasFragment;
import com.example.orderxpress.Inicio;
import com.example.orderxpress.MainActivityMenu;
import com.example.orderxpress.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivityHerramientas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int selectedTab=1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private LinearLayout calculadoraLayout, conversorLayout, notasLayout;
    private LottieAnimationView calculadoraAnimation, conversorAnimation, notasAnimation;
    private TextView calculadoraTxt, conversorTxt, notasTxt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_herramientas);
        //Enlace de cada uno de los LinearLayouts necesarios
        calculadoraLayout = findViewById(R.id.calculadoraLayout);
        conversorLayout = findViewById(R.id.conversorLayout);
        notasLayout =findViewById(R.id.notasLayout);

        //Enlace de cada uno de las vistas de Lottie
        calculadoraAnimation=findViewById(R.id.calculadoraAnimation);
        conversorAnimation=findViewById(R.id.conversorAnimation);
        notasAnimation=findViewById(R.id.notasAnimation);

        //Enlace de cada uno de los campos de texto del menú
        calculadoraTxt=findViewById(R.id.calculadoraTxt);
        conversorTxt=findViewById(R.id.conversorTxt);
        notasTxt=findViewById(R.id.notasTxt);

        calculadoraLayout.setBackgroundResource(R.drawable.menu);
        calculadoraTxt.setVisibility(View.VISIBLE);
        calculadoraAnimation.playAnimation();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new CalculadoraFragment()).commit();

        calculadoraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHeartClick();
            }
        });

        conversorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChatsClick();
            }
        });

        notasLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileClick();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView=findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.tool_open,R.string.tool_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_calculadora);
        }

    }
    public void onHeartClick(){
        if(selectedTab!=1){

            //Se colocan invisibles los textos
            conversorTxt.setVisibility(View.GONE);
            notasTxt.setVisibility(View.GONE);


            //Se cancelan las animaciones
            conversorAnimation.cancelAnimation();
            notasAnimation.cancelAnimation();


            //Se quitan los fondos de cada uno de los linear layout
            notasLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            conversorLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



            //Se destaca el elemento seleccionado
            calculadoraLayout.setBackgroundResource(R.drawable.menu);
            calculadoraTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            calculadoraLayout.startAnimation(scaleAnimation);
            calculadoraAnimation.playAnimation();
            selectedTab=1;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new CalculadoraFragment()).commit();

        }
    }

    private void onChatsClick() {
        if(selectedTab!=2){

            //Se colocan invisibles los textos
            calculadoraTxt.setVisibility(View.GONE);
            notasTxt.setVisibility(View.GONE);

            //Se cancelan las animaciones
            calculadoraAnimation.cancelAnimation();

            notasAnimation.cancelAnimation();

            //Se quitan los fondos de cada uno de los linear layout
            calculadoraLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            notasLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


            //Se destaca el elemento seleccionado
            conversorLayout.setBackgroundResource(R.drawable.menu);
            conversorTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            conversorLayout.startAnimation(scaleAnimation);
            conversorAnimation.playAnimation();
            selectedTab=2;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new ConversorFragment()).commit();
        }
    }

    private void onProfileClick() {
        if(selectedTab!=3){
            //Se colocan invisibles los textos
            conversorTxt.setVisibility(View.GONE);
            calculadoraTxt.setVisibility(View.GONE);

            //Se cancelan las animaciones
            conversorAnimation.cancelAnimation();
            calculadoraAnimation.cancelAnimation();


            //Se quitan los fondos de cada uno de los linear layout
            conversorLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            calculadoraLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



            //Se destaca el elemento seleccionado
            notasLayout.setBackgroundResource(R.drawable.menu);
            notasTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            notasLayout.startAnimation(scaleAnimation);
            notasAnimation.playAnimation();

            selectedTab=3;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new NotasFragment()).commit();
        }
    }
    private void cerrarSesion() {

        // Limpiar preferencias
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Lanzar la actividad de inicio de sesión
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav1_home:
                onHeartClick();
                break;
            case R.id.nav1_favorito:
                onChatsClick();
                break;
            case R.id.nav1_notificaciones:
                onProfileClick();
                break;
            case R.id.nav1_her:
                Intent intent = new Intent(this, MainActivityMenu.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav1_logout:
                cerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
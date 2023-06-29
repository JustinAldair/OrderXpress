package com.example.orderxpress;

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
import com.example.orderxpress.Fragment.FavoritoFragment;
import com.example.orderxpress.Fragment.HomeFragment;
import com.example.orderxpress.Fragment.NotificacionesFragment;
import com.example.orderxpress.Herramientas.MainActivityHerramientas;
import com.google.android.material.navigation.NavigationView;

public class MainActivityMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private int selectedTab=1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private LinearLayout homeLayout, rutasLayout, notificacionLayout;
    private LottieAnimationView homeAnimation, rutasAnimation, notificacionAnimation;
    private TextView homeTxt, rutasTxt, notificacionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Enlace de cada uno de los LinearLayouts necesarios
        homeLayout = findViewById(R.id.homeLayout);
        rutasLayout = findViewById(R.id.rutasLayout);
        notificacionLayout =findViewById(R.id.notificacionLayout);

        //Enlace de cada uno de las vistas de Lottie
        homeAnimation=findViewById(R.id.homeAnimation);
        rutasAnimation=findViewById(R.id.rutasAnimation);
        notificacionAnimation=findViewById(R.id.notificacionAnimation);

        //Enlace de cada uno de los campos de texto del menú
        homeTxt=findViewById(R.id.homeTxt);
        rutasTxt=findViewById(R.id.rutasTxt);
        notificacionTxt=findViewById(R.id.notificacionTxt);

        homeLayout.setBackgroundResource(R.drawable.menu);
        homeTxt.setVisibility(View.VISIBLE);
        homeAnimation.playAnimation();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHeartClick();
            }
        });

        rutasLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChatsClick();
            }
        });

        notificacionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProfileClick();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.tool_open,R.string.tool_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }
    public void onHeartClick(){
        if(selectedTab!=1){

            //Se colocan invisibles los textos
            rutasTxt.setVisibility(View.GONE);
            notificacionTxt.setVisibility(View.GONE);


            //Se cancelan las animaciones
            rutasAnimation.cancelAnimation();
            notificacionAnimation.cancelAnimation();


            //Se quitan los fondos de cada uno de los linear layout
            notificacionLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            rutasLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



            //Se destaca el elemento seleccionado
            homeLayout.setBackgroundResource(R.drawable.menu);
            homeTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            homeLayout.startAnimation(scaleAnimation);
            homeAnimation.playAnimation();
            selectedTab=1;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        }
    }

    private void onChatsClick() {
        if(selectedTab!=2){

            //Se colocan invisibles los textos
            homeTxt.setVisibility(View.GONE);
            notificacionTxt.setVisibility(View.GONE);

            //Se cancelan las animaciones
            homeAnimation.cancelAnimation();

            notificacionAnimation.cancelAnimation();

            //Se quitan los fondos de cada uno de los linear layout
            homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            notificacionLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


            //Se destaca el elemento seleccionado
            rutasLayout.setBackgroundResource(R.drawable.menu);
            rutasTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            rutasLayout.startAnimation(scaleAnimation);
            rutasAnimation.playAnimation();
            selectedTab=2;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new FavoritoFragment()).commit();
        }
    }

    private void onProfileClick() {
        if(selectedTab!=3){
            //Se colocan invisibles los textos
            rutasTxt.setVisibility(View.GONE);
            homeTxt.setVisibility(View.GONE);

            //Se cancelan las animaciones
            rutasAnimation.cancelAnimation();
            homeAnimation.cancelAnimation();


            //Se quitan los fondos de cada uno de los linear layout
            rutasLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



            //Se destaca el elemento seleccionado
            notificacionLayout.setBackgroundResource(R.drawable.menu);
            notificacionTxt.setVisibility(View.VISIBLE);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1.f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            notificacionLayout.startAnimation(scaleAnimation);
            notificacionAnimation.playAnimation();

            selectedTab=3;

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new NotificacionesFragment()).commit();
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
                Intent intent = new Intent(this, MainActivityHerramientas.class);
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
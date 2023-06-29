package com.example.orderxpress.Herramientas;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.airbnb.lottie.LottieAnimationView;
import com.example.orderxpress.Fragment.FavoritoFragment;
import com.example.orderxpress.Fragment.HomeFragment;
import com.example.orderxpress.Fragment.NotificacionesFragment;
import com.example.orderxpress.Fragment.PerfilFragment;
import com.example.orderxpress.Herramientas.Calculadora.CalculadoraFragment;
import com.example.orderxpress.Herramientas.Conversor.ConversorFragment;
import com.example.orderxpress.Herramientas.Notas.NotasFragment;
import com.example.orderxpress.R;
import com.example.orderxpress.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivityHerramientas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_herramientas);
        navigationView = findViewById(R.id.nav_view_herramientas);
        Toolbar toolbar = findViewById(R.id.toolbar_herramientas);
        drawerLayout = findViewById(R.id.drawer_layout_herramientas);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        drawerLayout = findViewById(R.id.drawer_layout_herramientas);

        bottomNavigationView = findViewById(R.id.bottomNavigationView_Herramientas);

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int animationHeight = 100; // Alto deseado en píxeles
            int topMargin = 20; // Margen superior en píxeles
            switch (item.getItemId()) {
                case R.id.nav_calculadora:
                    replaceFragment(new CalculadoraFragment());

                    // Cargar y reproducir la animación Lottie
                    LottieAnimationView animationView = new LottieAnimationView(this);
                    animationView.setAnimation(R.raw.calculadora);
                    animationView.playAnimation();

                    // Obtener el contenedor FrameLayout del fragmento
                    FrameLayout frameLayout = findViewById(R.id.nav_calculadora);
                    frameLayout.removeAllViews(); // Limpiar cualquier vista anterior

                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.WRAP_CONTENT,
                            animationHeight
                    );
                    layoutParams.topMargin = topMargin; // Establecer el margen superior
                    animationView.setLayoutParams(layoutParams);

                    // Agregar el LottieAnimationView al contenedor FrameLayout
                    frameLayout.addView(animationView);

                    break;
                case R.id.nav_conversor:
                    replaceFragment(new ConversorFragment());
                    LottieAnimationView loveView = new LottieAnimationView(this);
                    loveView.setAnimation(R.raw.conversor);
                    loveView.playAnimation();
                    FrameLayout loveLayout = findViewById(R.id.nav_conversor);
                    loveLayout.removeAllViews();

                    ViewGroup.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    );
                    loveView.setLayoutParams(layoutParams1);
                    loveLayout.addView(loveView);
                    break;

                case R.id.nav_notas:
                    replaceFragment(new NotasFragment());
                    LottieAnimationView notifyView = new LottieAnimationView(this);
                    notifyView.setAnimation(R.raw.blog);
                    notifyView.playAnimation();
                    FrameLayout notifyLayout = findViewById(R.id.nav_notas);
                    notifyLayout.removeAllViews();

                    ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                    );
                    notifyView.setLayoutParams(layoutParams2);
                    notifyLayout.addView(notifyView);
                    break;

            }
            return true;
        });
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_herramientas, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Entro a seleccionar una", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.nav1_home:
                getSupportFragmentManager().beginTransaction().replace(androidx.fragment.R.id.fragment_container_view_tag, new HomeFragment()).commit();
                navigationView.setCheckedItem(R.id.nav1_home);
                break;
            case R.id.nav1_favorito:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_herramientas, new FavoritoFragment()).commit();
                navigationView.setCheckedItem(R.id.nav1_favorito);
                break;
            case R.id.nav1_notificaciones:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_herramientas, new NotificacionesFragment()).commit();
                navigationView.setCheckedItem(R.id.nav1_notificaciones);
                break;
            case R.id.nav1_perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_herramientas, new PerfilFragment()).commit();
                navigationView.setCheckedItem(R.id.nav1_perfil);
                break;
            case R.id.nav1_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                navigationView.setCheckedItem(R.id.nav1_logout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
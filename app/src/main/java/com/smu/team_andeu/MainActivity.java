package com.smu.team_andeu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_app_bar);

        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment
        );

        //https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
        //https://developer.android.com/guide/navigation/navigation-ui?hl=ko#add_a_navigation_drawer
        //  AppBarConfiguration 및 탐색 창 추가
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
             //   .setOpenableLayout()
                .build();
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration
        );

        // https://developer.android.com/guide/navigation/navigation-ui?hl=ko#bottom_navigation
        // 하단 탐색
        bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav,navController);

        // https://developer.android.com/guide/navigation/navigation-ui?hl=ko#listen_for_navigation_events
        // 탐색 이벤트 수신 대기
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getId() == R.id.home_dest){
                toolbar.setVisibility(View.VISIBLE);
                bottomNav.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
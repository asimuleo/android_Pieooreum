package com.smu.team_andeu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartActivity extends AppCompatActivity {
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        navController = Navigation.findNavController(
                this, R.id.nav_host_fragment2
        );


    }

    public void showProgress() {
        navController.navigate(R.id.action_start_dest_to_progress_dest);
    }

    public void showBreakToProgress(){
        navController.navigate(R.id.action_progress_break_dest_to_progress_dest);
    }

    public void showBreak() {
        navController.navigate(R.id.action_progress_dest_to_progress_break_dest);
    }

    public void showEnd() {
        navController.navigate(R.id.action_progress_dest_to_exerEndFragment);
    }
}

package com.smu.team_andeu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smu.team_andeu.data.RoutineWithDexers;
import com.smu.team_andeu.model.Exer;
import com.smu.team_andeu.nav.DExerFragment;
import com.smu.team_andeu.nav.DRoutineFragment;


public class MainActivity extends AppCompatActivity {

    //private AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNav;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(
                this, R.id.nav_host_fragment
        );

        // 앱바가 필요하면 이걸 쓰면 된다.
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
//             //   .setOpenableLayout()
//                .build();
//        NavigationUI.setupWithNavController(
//                toolbar, navController, appBarConfiguration
//        );
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            if(destination.getId() == R.id.home_dest){
//                toolbar.setVisibility(View.VISIBLE);
//                bottomNav.setVisibility(View.VISIBLE);
//            }
//        });

        bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    // for Exer List
    public void showExer(Exer exer) {
        Bundle bundle = DExerFragment.getBundleWithId(exer.getExerId());
        navController.navigate(R.id.action_DRoutineFragment_to_DExerFragment, bundle);
    }

    // for Routine List
    public void showRoutine(RoutineWithDexers routineWithDexers) {
        Bundle bundle = DRoutineFragment.getBundleWithId(routineWithDexers.getRoutine().getRoutineId());
        navController.navigate(R.id.action_routine_dest_to_detail_routine_dest, bundle);
    }

    @Override
    public void onBackPressed() {
        int i = navController.getCurrentDestination().getId();
        if (!navController.popBackStack()) {
            int id;
            if (i == R.id.myPage_dest) {
                new AlertDialog.Builder(this)
                        .setTitle(getApplication().getPackageName())
                        .setMessage("Phomer를 종료하시겠습니까?")
                        .setPositiveButton("종료", (dialog, which) -> finish())
                        .setNegativeButton("아니요", null)
                        .show();
            } else if (i == (id = R.id.routine_dest) || i == (id = R.id.setting_dest) || i == (id = R.id.training_dest)) {
                navController.navigate(id);
            }
        }

    }
}
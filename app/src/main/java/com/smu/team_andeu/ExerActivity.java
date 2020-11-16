package com.smu.team_andeu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smu.team_andeu.model.Exer;

public class ExerActivity extends AppCompatActivity {

    //kjsda;lkgja;lksdjg

    //g asfas dadsfsdfsdfsdfsdf

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer);

        if (savedInstanceState == null) {
            ExerListFragment fragment = new ExerListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ExerListFragment.TAG).commit();
        }
    }

    // 선택된 exer의  deatil을 보여줄 Fragment 를 생성 한다.
    public void show(Exer exer) {
        ExerFragment exerFragment = ExerFragment.forExer(exer.getExerId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("exer")
                .replace(R.id.fragment_container,
                        exerFragment, null).commit();
    }
}
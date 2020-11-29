package com.smu.team_andeu;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.smu.team_andeu.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ProgressActivity extends Activity {
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exer_progress);

        textView = findViewById(R.id.count_txt);

        long duration = TimeUnit.MINUTES.toMillis(1);

        //Initialize countdown timer
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //convert millisecond to minute and second
                String sDuration = String.format(Locale.KOREA, "%02d : %02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(1)
                        ,TimeUnit.MILLISECONDS.toSeconds(1) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
                //Set converted string on text view
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {
                //When finish
                //Hide text view
                textView.setVisibility(View.GONE);
                //Display toast
                Toast.makeText(getApplicationContext()
                        , "Countdown timer has ended", Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}

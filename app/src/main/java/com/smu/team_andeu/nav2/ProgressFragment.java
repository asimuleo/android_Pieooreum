package com.smu.team_andeu.nav2;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.smu.team_andeu.R;
import com.smu.team_andeu.StartActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ProgressFragment extends Fragment {
    TextView textView;

    CountDownTimer c;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.exer_progress, container, false);

       textView = v.findViewById(R.id.count_txt);
       long duration = TimeUnit.MINUTES.toMillis(1);

        //Initialize countdown timer
        c = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                //convert millisecond to minute and second
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                //Set converted string on text view
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {
                //When finish
                //Hide text view
                textView.setVisibility(View.GONE);
                if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    ((StartActivity) requireActivity()).showBreak();
                }
            }
        };
        c.start();

        Button cancel_button = v.findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(v1 -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((StartActivity) requireActivity()).showEnd();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        c.cancel();
    }
}

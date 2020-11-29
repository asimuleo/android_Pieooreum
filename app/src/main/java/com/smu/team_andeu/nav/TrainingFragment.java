package com.smu.team_andeu.nav;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.viewmodels.TrainingListViewModel;

public class TrainingFragment extends Fragment {

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;

    ViewGroup linearView;
    Runnable r;


    HorizontalScrollView scrollView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.training_fragment, container, false);

        linearView = view.findViewById(R.id.routine_linear);

        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        spinner3 = view.findViewById(R.id.spinner3);

        final TrainingListViewModel viewModel = new ViewModelProvider(this).get(TrainingListViewModel.class);

        String[] from = {"r_name"};
        int[] to = {android.R.id.text1};
        SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, viewModel.getCursor(0), from, to, 0);
        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, viewModel.getCursor(1), from, to, 0);
        SimpleCursorAdapter adapter3 = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, viewModel.getCursor(2), from, to, 0);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);


        button1 = view.findViewById(R.id.plus_spinner1);
        button2 = view.findViewById(R.id.plus_spinner2);
        button3 = view.findViewById(R.id.plus_spinner3);

        button1.setOnClickListener(v -> {
            Cursor c = (Cursor) spinner1.getSelectedItem();
            addRoutine(c.getString(1));
        });
        button2.setOnClickListener(v -> {
            Cursor c = (Cursor) spinner2.getSelectedItem();
            addRoutine(c.getString(1));
        });
        button3.setOnClickListener(v -> {
            Cursor c = (Cursor) spinner3.getSelectedItem();
            addRoutine(c.getString(1));
        });

        ImageButton clear = view.findViewById(R.id.clear_button);
        clear.setOnClickListener(v -> linearView.removeAllViews());

        ImageButton start = view.findViewById(R.id.start_button);
        start.setOnClickListener(v -> {
            scrollView.removeCallbacks(r);
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) requireActivity()).showStart();
            }
        });


        r = () -> scrollView.fullScroll(ScrollView.FOCUS_RIGHT);
        scrollView = view.findViewById(R.id.horizontal_scroll);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    // 맞춤 레이아웃 애니메이션을 제공하려면 LayoutTransition 객체를 만들고 setLayoutTransition() 메서드를 사용하여 레이아웃에 제공하세요.
    private void addRoutine(CharSequence text) {
        scrollView.removeCallbacks(r);
        Button button = new Button(getContext());
        button.setTag("Rbutton_" + text);
        button.setText(text);
        button.setOnClickListener(v -> linearView.removeView(v));
        linearView.addView(button);
        scrollView.postDelayed(r, 300);
    }
}

package com.smu.team_andeu.nav;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.R;
import com.smu.team_andeu.adapters.TrainingCursorAdapter;
import com.smu.team_andeu.viewmodels.TrainingListViewModel;

public class TrainingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.training_fragment, container, false);

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

    // 리스너
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

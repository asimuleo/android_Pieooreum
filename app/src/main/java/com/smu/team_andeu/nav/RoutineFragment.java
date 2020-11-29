package com.smu.team_andeu.nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.adapters.RoutineListAdapter;
import com.smu.team_andeu.callback.RoutineClickCallback;
import com.smu.team_andeu.data.RoutineWithDexers;
import com.smu.team_andeu.databinding.RoutineFragmentBinding;
import com.smu.team_andeu.viewmodels.RoutineListViewModel;

import java.util.List;

public class RoutineFragment extends Fragment {

    private RoutineListAdapter mRoutineListAdapter;

    RoutineFragmentBinding mBinding;

    TabLayout tabs;

    // 콜백 생성
    private final RoutineClickCallback mRoutineClickCallback = routineWithDexers -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) requireActivity()).showRoutine(routineWithDexers);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.routine_fragment, container, false);

        mRoutineListAdapter = new RoutineListAdapter(mRoutineClickCallback);
        mBinding.routinesList.setAdapter(mRoutineListAdapter);

        tabs = mBinding.tabs;
        tabs.addTab(tabs.newTab().setText("All"));
        tabs.addTab(tabs.newTab().setText("Favorite"));
        tabs.addTab(tabs.newTab().setText("Basic"));
        tabs.addTab(tabs.newTab().setText("Phomer"));


        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RoutineListViewModel viewModel =
                new ViewModelProvider(this).get(RoutineListViewModel.class);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int category = tab.getPosition();
                viewModel.setQuery(category);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        subscribeUi(viewModel.getmRoutines());
    }

    private void subscribeUi(LiveData<List<RoutineWithDexers>> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), routineWithDexers -> {
            if (routineWithDexers != null) {
                mBinding.setIsLoading(false);
                mRoutineListAdapter.setRoutineList(routineWithDexers);
            } else {
                mBinding.setIsLoading(true);
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding.executePendingBindings();
        });
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        mRoutineListAdapter = null;
        super.onDestroyView();
    }
}

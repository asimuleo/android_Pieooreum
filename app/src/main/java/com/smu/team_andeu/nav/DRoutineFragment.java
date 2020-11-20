package com.smu.team_andeu.nav;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.adapters.ExerListAdapter;
import com.smu.team_andeu.callback.ExerClickCallback;

import com.smu.team_andeu.data.Exercise;
import com.smu.team_andeu.databinding.DRoutineFragmentBinding;
import com.smu.team_andeu.viewmodels.ExerListViewModel;

import java.util.List;

public class DRoutineFragment extends Fragment {
    public static final String TAG = "ExerListFragment";

    private ExerListAdapter mExerListAdapter;

    DRoutineFragmentBinding mBinding;

    // 콜백 생성
    private final ExerClickCallback mExerClickCallback = exer -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) requireActivity()).show(exer);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.d_routine_fragment, container, false);

        // 프래그먼트 생성과 함께 어댑터 생성
        mExerListAdapter = new ExerListAdapter(mExerClickCallback);
        mBinding.exersList.setAdapter(mExerListAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ExerListViewModel viewModel =
                new ViewModelProvider(this).get(ExerListViewModel.class);
        mBinding.exerSearchButton.setOnClickListener(v -> {
            Editable query = mBinding.exerSearchBox.getText();
            viewModel.setQuery(query);
        });
        subscribeUi(viewModel.getmExers());
    }

    private void subscribeUi(LiveData<List<Exercise>> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), myExers -> {
            if (myExers != null) {
                mBinding.setIsLoading(false);
                mExerListAdapter.setExerList(myExers);
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
        mExerListAdapter = null;
        super.onDestroyView();
    }

}

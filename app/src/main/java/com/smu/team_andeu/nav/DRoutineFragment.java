package com.smu.team_andeu.nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.adapters.DexerListAdapter;
import com.smu.team_andeu.callback.DexerClickCallback;

import com.smu.team_andeu.data.RoutineRepository;
import com.smu.team_andeu.data.RoutineWithDexers;
import com.smu.team_andeu.databinding.DRoutineFragmentBinding;
import com.smu.team_andeu.viewmodels.DexerListViewModel;



public class DRoutineFragment extends Fragment {
    private static final String KEY_ROUTINE_ID = "routine_id";

    DexerListAdapter dexerListAdapter;
    DRoutineFragmentBinding mBinding;

    FloatingActionButton floatingActionButton;
    ImageButton edit_name;
    ImageButton delete_routine;

    // 콜백 생성
    private final DexerClickCallback mDexerClickCallback = dexer -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) requireActivity()).showExerWithDexer(dexer);
        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.d_routine_fragment, container, false);
        dexerListAdapter = new DexerListAdapter(mDexerClickCallback);
        mBinding.routinesList.setAdapter(dexerListAdapter);
        floatingActionButton = mBinding.addExerButton;
        edit_name = mBinding.editNameButton;
        delete_routine = mBinding.deleteButton;

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        DexerListViewModel.Factory factory = new DexerListViewModel.Factory(
                requireActivity().getApplication(), requireArguments().getInt(KEY_ROUTINE_ID));

        final DexerListViewModel viewModel = new ViewModelProvider(this, factory)
                .get(DexerListViewModel.class);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setDexerListViewModel(viewModel);

        floatingActionButton.setOnClickListener(v -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) requireActivity()).showAddExer(
                        viewModel.getRoutineWithDexers().getValue().getRoutine());
            }
        });

        edit_name.setOnClickListener(v -> {
            // routine Id 를 전달 함으로서 RenameFragment가 어떤 루틴인지 알 수 있게 해준다.
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) requireActivity()).showRoutineRename(
                        requireArguments().getInt(KEY_ROUTINE_ID));
            }
        });

        // 해당루틴 삭제
        delete_routine.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setTitle(getActivity().getApplication().getPackageName())
                .setMessage(mBinding.routineName.getText() + " 를 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                        ((MainActivity) requireActivity()).pop();
                    }
                    RoutineRepository.getInstance(getActivity().getApplication()).removeRoutineById(
                            requireArguments().getInt(KEY_ROUTINE_ID));
                })
                .setNegativeButton("아니요", ((dialog, which) -> {
                }))
                .show());

        subscribeToModel(viewModel.getRoutineWithDexers());
    }

    // FAB를 클릭 했을시 작동되는 콜백 메소드


    private void subscribeToModel( LiveData<RoutineWithDexers> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), myDexers -> {
            if (myDexers != null) {
                mBinding.setIsLoading(false);
                dexerListAdapter.setDexerList(myDexers.getDexers());
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
        dexerListAdapter = null;
        super.onDestroyView();
    }

    public static Bundle getBundleWithId(int routineId) {
        Bundle args = new Bundle();
        args.putInt(KEY_ROUTINE_ID, routineId);
        return args;
    }
}

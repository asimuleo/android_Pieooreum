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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.data.Dexer;
import com.smu.team_andeu.data.DexerRepository;
import com.smu.team_andeu.databinding.DExerFragmentBinding;
import com.smu.team_andeu.viewmodels.DetailDexerViewModel;
import com.smu.team_andeu.viewmodels.ExerViewModel;

public class DExerFragment extends Fragment {
    private static final String KEY_Exer_ID = "exer_id";
    private static final String KEY_ADD = "Add";
    private static final String KEY_ROUTINE_ID = "routine_id";
    private static String KEY_Dexer_ID = "dexer_id";

    DExerFragmentBinding mBinding;
    Button button;

    //TODO Something을 위한 어뎁터가 필요함.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.d_exer_fragment, container, false);
        button = mBinding.addButton;
        //TODO  Create and set the adapter for the RecyclerView

        return mBinding.getRoot();
    }

    //TODO Something을 클릭 했을시 작동되는 콜백 메소드도 있으면 좋음
//    private final SomethingClickCallback mSomethingClickCallback = something -> {
//        // no-op
//    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Exer 상세정보 부분
        ExerViewModel.Factory factory = new ExerViewModel.Factory(
                requireActivity().getApplication(), requireArguments().getInt(KEY_Exer_ID));

        final ExerViewModel model = new ViewModelProvider(this, factory)
                .get(ExerViewModel.class);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setExerViewModel(model);


        DetailDexerViewModel.Factory factory1 = new DetailDexerViewModel.Factory(
                requireActivity().getApplication(), requireArguments().getInt(KEY_Dexer_ID));
        final DetailDexerViewModel model1 = new ViewModelProvider(this, factory1)
                .get(DetailDexerViewModel.class);
        mBinding.setDetailDexerViewMoidel(model1);


        button.setOnClickListener(v -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) requireActivity()).showAddDetailDexer(
                        requireArguments().getInt(KEY_ROUTINE_ID),
                        requireArguments().getInt(KEY_Exer_ID));
            }
        });

        subscribeToModel(model);
    }

    private void subscribeToModel(final ExerViewModel model) {
        // 옵저버 등록
        // 아직 변하는 데이타가 없기 때문에 무한 Loading 상태로 만든다.
        mBinding.setIsLoading(true);
        mBinding.setIsFromAdd(requireArguments().getBoolean(KEY_ADD));
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        //TODO 어뎁터 null
        super.onDestroyView();
    }

    public static Bundle getBundleWithId(int exerId, int routineId) {
        Bundle args = new Bundle();
        args.putInt(KEY_Exer_ID, exerId);
        args.putBoolean(KEY_ADD, true);
        args.putInt(KEY_ROUTINE_ID, routineId);
        return args;
    }

    public static Bundle getBundleWithDexerId(int exerId, int dexerId) {
        Bundle args = new Bundle();
        args.putInt(KEY_Exer_ID, exerId);
        args.putInt(KEY_Dexer_ID, dexerId);
        args.putBoolean(KEY_ADD, false);
        return args;
    }
}

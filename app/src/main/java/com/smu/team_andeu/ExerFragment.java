package com.smu.team_andeu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.databinding.FragmentExerBinding;
import com.smu.team_andeu.viewmodels.ExerViewModel;

public class ExerFragment extends Fragment {
    private static final String KEY_Exer_ID = "exer_id";

    FragmentExerBinding mBinding;

    //TODO Something을 위한 어뎁터가 필요함.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_exer, container, false);

        //TODO  Create and set the adapter for the RecyclerView

        return mBinding.getRoot();
    }

    //TODO Something을 클릭 했을시 작동되는 콜백 메소드도 있으면 좋음
//    private final SomethingClickCallback mSomethingClickCallback = something -> {
//        // no-op
//    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ExerViewModel.Factory factory = new ExerViewModel.Factory(
                requireActivity().getApplication(), requireArguments().getInt(KEY_Exer_ID));

        final ExerViewModel model = new ViewModelProvider(this, factory)
                .get(ExerViewModel.class);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setExerViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final ExerViewModel model) {
        // 옵저버 등록
        // 아직 변하는 데이타가 없기 때문에 무한 Loading 상태로 만든다.
        mBinding.setIsLoading(true);
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        //TODO 어뎁터 null
        super.onDestroyView();
    }

    // 특정한 id를 통해 exer fragment 를 생성 후 반환.
    public static ExerFragment forExer(int exerId) {
        ExerFragment fragment = new ExerFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_Exer_ID, exerId);
        fragment.setArguments(args);
        return fragment;
    }
}

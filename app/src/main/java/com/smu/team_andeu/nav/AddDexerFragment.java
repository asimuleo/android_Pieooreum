package com.smu.team_andeu.nav;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.data.Dexer;
import com.smu.team_andeu.data.DexerRepository;
import com.smu.team_andeu.data.Exercise;
import com.smu.team_andeu.data.ExerciseRepository;
import com.smu.team_andeu.data.RoutineRepository;
import com.smu.team_andeu.data.RoutineOrder;

public class AddDexerFragment extends Fragment {

    private static final String KEY_ROUTINE_ID = "routine_id";
    private static final String KEY_EXER_ID = "exer_id";

    boolean b;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_dexer_fragment, container, false);
        final TextInputLayout nameTextInput = view.findViewById(R.id.nickname_text_input);
        final TextInputEditText nameEditText = view.findViewById(R.id.nickname_text_edit);
        final TextInputLayout intTextInput = view.findViewById(R.id.int_input);
        final TextInputEditText intEditText = view.findViewById(R.id.int_edit);

        MaterialButton addButton = view.findViewById(R.id.add_button);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);

        addButton.setOnClickListener(v -> {
            if(!isNameValid(nameEditText.getText())){
                nameTextInput.setError(getString(R.string.pho_error_name));
            }else if(!isIntValid(intEditText.getText())){
                nameTextInput.setError(null);// Clear the error
                cancelButton.setError(getString(R.string.pho_error_int));
            }else{
                nameTextInput.setError(null);
                intTextInput.setError(null);

                // dexer add 하기
                Dexer dexer = makeDexer(nameEditText.getText(), intEditText.getText());
                DexerRepository.getInstance(getActivity().getApplication()).insert(dexer);

                if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    ((MainActivity) requireActivity()).showAddResult(
                            requireArguments().getInt(KEY_ROUTINE_ID));
                }
            }
        });

        nameEditText.setOnKeyListener((v, keyCode, event) -> {
            if(isNameValid(nameEditText.getText())){
                nameTextInput.setError(null); //Clear the error
            }
            return false;
        });

        intEditText.setOnKeyListener((v, keyCode, event) -> {
            if(isIntValid(intEditText.getText())){
                intTextInput.setError(null); //Clear the error
            }
            return false;
        });
        intTextInput.setHint(b?"Time":"Count");
        return view;
    }

    private Dexer makeDexer(Editable nickname, Editable i) {

        Exercise e = ExerciseRepository.getInstance(getActivity().getApplication()).getExerById(
                requireArguments().getInt(KEY_EXER_ID))
                .getValue();
        int maxOrder = RoutineRepository.getInstance(getActivity().getApplication()).getRoutine(
                requireArguments().getInt(KEY_ROUTINE_ID))
                .getValue()
                .getMaxOrder();
        // Count Time 정하기
        b = e.isTime();
        int d_time = 0;
        int d_count = 0;
        int d_calories;
        if(b){
            d_time = Integer.valueOf(i.toString());
            d_calories = (int) (d_time * e.getCalorie());
        }else{
            d_count = Integer.valueOf(i.toString());
            d_calories = (int) (d_count * e.getCalorie());
        }

        Dexer dexer = new Dexer(0,
                requireArguments().getInt(KEY_EXER_ID),
                nickname.toString(),
                d_count,
                d_time,
                d_calories,
                requireArguments().getInt(KEY_ROUTINE_ID),
                maxOrder);

        RoutineRepository.getInstance(getActivity().getApplication()).updateRoutineMaxorder(new RoutineOrder(
                requireArguments().getInt(KEY_ROUTINE_ID),maxOrder+1
        ));
        return dexer;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean isIntValid(@Nullable Editable text) {
        if(text == null || text.length() == 0) return false;
        int i = Integer.valueOf(text.toString());
        return i >= 1;
    }

    private boolean isNameValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }



    public static Bundle getBundleWithId(int routineId, int exerId){
        Bundle args = new Bundle();
        args.putInt(KEY_ROUTINE_ID, routineId);
        args.putInt(KEY_EXER_ID, exerId);
        return args;
    }
}

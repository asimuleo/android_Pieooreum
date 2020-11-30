package com.smu.team_andeu.nav;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.R;
import com.smu.team_andeu.data.RoutineName;
import com.smu.team_andeu.data.RoutineRepository;

public class RenameRoutineFragment extends Fragment {

    private static final String KEY_ROUTINE_ID = "routine_id";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rename_routine_fragment, container, false);
        final TextInputLayout renameTextInput = view.findViewById(R.id.rename_text_input);
        final TextInputEditText renameEditText = view.findViewById(R.id.rename_text_edit);

        MaterialButton renameButton = view.findViewById(R.id.rename_button);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);

        renameButton.setOnClickListener(v -> {
            if(!isNameValid(renameEditText.getText())) {
                renameTextInput.setError(getString(R.string.pho_error_rname));
            }else{
                renameTextInput.setError(null);
                // 이름 업데이트
                RoutineRepository.getInstance(getActivity().getApplication())
                        .updateRoutineName(
                                new RoutineName(
                                        requireArguments().getInt(KEY_ROUTINE_ID)
                                        ,renameEditText.getText().toString()
                                )
                        );
                // 전으로 이동
                if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    ((MainActivity) requireActivity()).showResultRoutineRename(
                            requireArguments().getInt(KEY_ROUTINE_ID));
                }
            }
        });

        cancelButton.setOnClickListener(v -> Toast.makeText(getContext(), "미 구현", Toast.LENGTH_SHORT).show());

        renameEditText.setOnKeyListener((v, keyCode, event) -> {
            if(isNameValid(renameEditText.getText())){
                renameTextInput.setError(null);
            }else{
                renameTextInput.setError(getString(R.string.pho_error_rname));
            }
            return false;
        });

        return view;
    }

    private boolean isNameValid(@Nullable Editable text) {
        return text != null && text.length() >= 1 && text.length() <= 8;
    }

    public static Bundle getBundleWithId(int routineId){
        Bundle args = new Bundle();
        args.putInt(KEY_ROUTINE_ID, routineId);
        return args;
    }
}

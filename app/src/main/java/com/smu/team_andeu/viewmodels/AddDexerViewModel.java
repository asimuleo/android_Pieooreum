package com.smu.team_andeu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import com.smu.team_andeu.data.Dexer;
import com.smu.team_andeu.data.DexerRepository;
import com.smu.team_andeu.data.Exercise;
import com.smu.team_andeu.data.ExerciseRepository;
import com.smu.team_andeu.data.Routine;
import com.smu.team_andeu.data.RoutineOrder;
import com.smu.team_andeu.data.RoutineRepository;




public class AddDexerViewModel extends AndroidViewModel {
    private static final String QUERY_KEY_R = "QUERYR";
    private static final String QUERY_KEY_E = "QUERYE";

    private final SavedStateHandle mSavedStateHandler;
    private final RoutineRepository mRoutineRepository;
    private final DexerRepository mDexerRepository;

    private final LiveData<Routine> mRoutine;
    private final LiveData<Exercise> mExercise;

    public AddDexerViewModel(@NonNull Application application,
                             @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;

        mRoutineRepository = RoutineRepository.getInstance(application);
        mRoutine = Transformations.switchMap(
                mSavedStateHandler.getLiveData(QUERY_KEY_R, null),
                mRoutineRepository::getRoutine
        );

        ExerciseRepository mExerciseRepository = ExerciseRepository.getInstance(application);
        mExercise = Transformations.switchMap(
                mSavedStateHandler.getLiveData(QUERY_KEY_E, null),
                mExerciseRepository::getExerById
        );
        mDexerRepository = DexerRepository.getInstance(application);
    }

    public void setQuery(int rquery, int equery) {
        // Save the user's query into the SavedStateHandle.
        // This ensures that we retain the value across process death
        // and is used as the input into the Transformations.switchMap above
        mSavedStateHandler.set(QUERY_KEY_R, rquery);
        mSavedStateHandler.set(QUERY_KEY_E, equery);
    }

    public LiveData<Routine> getmRoutine() {return mRoutine;}
    public LiveData<Exercise> getmExercise() {return mExercise;}

    public void insertDexer(Dexer dexer){mDexerRepository.insert(dexer);}
    public void updateRoutineMaxOrder(RoutineOrder routine){mRoutineRepository.updateRoutineMaxorder(routine);}
}

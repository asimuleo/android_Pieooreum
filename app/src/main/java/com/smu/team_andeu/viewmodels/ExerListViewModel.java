package com.smu.team_andeu.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import com.smu.team_andeu.data.Exercise;
import com.smu.team_andeu.data.ExerciseRepository;

import java.util.List;

public class ExerListViewModel extends AndroidViewModel {
    private static final String QUERY_KEY = "QUERY";

    private SavedStateHandle mSavedStateHandler;
    private ExerciseRepository exerciseRepository;
    private LiveData<List<Exercise>> mExers;

    public ExerListViewModel(@NonNull Application application,
                             @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;
        exerciseRepository = new ExerciseRepository(application);
        mExers = Transformations.switchMap(
                savedStateHandle.getLiveData("QUERY", null),
                (Function<CharSequence, LiveData<List<Exercise>>>) query -> {
                    // 검색 창이 비었으면 다 불러옴.
                    if (TextUtils.isEmpty(query)) {
                        return exerciseRepository.getExers();
                    }
                    return exerciseRepository.searchExers("*" + query + "*");
                }
        );
    }

    public void setQuery(CharSequence query) {
        // Save the user's query into the SavedStateHandle.
        // This ensures that we retain the value across process death
        // and is used as the input into the Transformations.switchMap above
        mSavedStateHandler.set(QUERY_KEY, query);
    }


    public LiveData<List<Exercise>> getmExers() {
        return mExers;
    }
}

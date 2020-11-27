package com.smu.team_andeu.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import com.smu.team_andeu.data.RoutineWithDexers;
import com.smu.team_andeu.data.RoutineWithDexersRepository;

import java.util.List;

public class RoutineListViewModel extends AndroidViewModel {
    private static final String QUERY_KEY = "QUERY";

    private final SavedStateHandle mSavedStateHandler;
    private final RoutineWithDexersRepository routineWithDexersRepository;
    private final LiveData<List<RoutineWithDexers>> mRoutines;

    public RoutineListViewModel(@NonNull Application application,
                                @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;
        routineWithDexersRepository = RoutineWithDexersRepository.getInstance(application);
        mRoutines = Transformations.switchMap(
                savedStateHandle.getLiveData("QUERY", null),
                (Function<Integer, LiveData<List<RoutineWithDexers>>>) query -> {
                    // 카테고리가 설정되지 않았다면 다불러옴
                    if (query == null || query == -1) {
                        return routineWithDexersRepository.getRoutines();
                    }
                    return routineWithDexersRepository.getRoutinesByCategory(query);
                }
        );
    }

    public void setQuery(int query) {
        // Save the user's query into the SavedStateHandle.
        // This ensures that we retain the value across process death
        // and is used as the input into the Transformations.switchMap above
        mSavedStateHandler.set(QUERY_KEY, query);
    }

    public LiveData<List<RoutineWithDexers>> getmRoutines() {return mRoutines;}
}

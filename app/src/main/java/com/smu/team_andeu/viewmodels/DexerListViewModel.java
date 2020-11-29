package com.smu.team_andeu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.data.ExerciseRepository;
import com.smu.team_andeu.data.RoutineWithDexers;
import com.smu.team_andeu.data.RoutineWithDexersRepository;

public class DexerListViewModel extends AndroidViewModel {

    private final LiveData<RoutineWithDexers> mObservableRoutineWithDexers;

    public DexerListViewModel(@NonNull Application application, RoutineWithDexersRepository repository,
                              final int routineId) {
        super(application);
        mObservableRoutineWithDexers = repository.getRoutinesById(routineId);
    }

    public LiveData<RoutineWithDexers> getRoutineWithDexers(){return mObservableRoutineWithDexers;}

    // 해당하는 routineId로 부터 DexerListViewModel을 만든다.
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mRoutineId;

        private final RoutineWithDexersRepository mRepository;

        public Factory(@NonNull Application application, int routineId) {
            mApplication = application;
            mRoutineId = routineId;
            mRepository = RoutineWithDexersRepository.getInstance(application);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new DexerListViewModel(mApplication, mRepository, mRoutineId);
        }
    }
}

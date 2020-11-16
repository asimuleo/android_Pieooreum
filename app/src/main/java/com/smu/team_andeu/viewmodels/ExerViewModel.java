package com.smu.team_andeu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.MainActivity;
import com.smu.team_andeu.data.Exercise;
import com.smu.team_andeu.data.ExerciseRepository;

public class ExerViewModel extends AndroidViewModel {
    //TODO 운동 에 대한 세부사항의 데이타의 구현이 필요.

    private LiveData<Exercise> mObservableExer;

    private final int mExerId;

    public ExerViewModel(@NonNull Application application, ExerciseRepository repository,
                         final int exerId) {
        super(application);
        mExerId = exerId;

        mObservableExer = repository.getExerById(mExerId);
        //TODO 운동의 상세설명 에 필요한 내용을 다른 repository로 부터 가져오면 된다.
    }

    public LiveData<Exercise> getExer() {
        return mObservableExer;
    }

    // 해당하는 exerId로 부터 만든다.
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mExerId;

        private final ExerciseRepository mRepository;

        public Factory(@NonNull Application application, int exerId) {
            mApplication = application;
            mExerId = exerId;
            mRepository = ExerciseRepository.getInstance(application);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ExerViewModel(mApplication, mRepository, mExerId);
        }
    }

}

package com.smu.team_andeu.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

// 다음을 참고
// https://github.com/android/architecture-components-samples/tree/main/BasicSample
public class ExerciseRepository {
    // 싱글톤
    private static ExerciseRepository sInstance;
    private final ExerciseDao mExerDao;

    public ExerciseRepository(final Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mExerDao = db.exerDao();

    }

    public static ExerciseRepository getInstance(final Application application) {
        if (sInstance == null) {
            synchronized (ExerciseRepository.class) {
                if (sInstance == null) {
                    sInstance = new ExerciseRepository(application);
                }
            }
        }
        return sInstance;
    }

    // 운동들
    public LiveData<List<Exercise>> getExers() {
        return mExerDao.getExers();
    }

    // 운동을 이름과 설명에서 검색
    public LiveData<List<Exercise>> searchExers(String query) {
        return mExerDao.searchExers(query);
    }

    // 운동 하나
    public LiveData<Exercise> getExerById(final int exerId) {
        return mExerDao.getExer(exerId);
    }

}

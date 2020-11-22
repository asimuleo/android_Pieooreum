package com.smu.team_andeu.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RoutineWithDexersRepository {

    private static RoutineWithDexersRepository sInstance;
    private final RoutineWithDexersDao routineWithDexersDao;

    public RoutineWithDexersRepository(final Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        routineWithDexersDao = db.routineWithDexersDao();
    }

    public static RoutineWithDexersRepository getInstance(final Application application) {
        if (sInstance == null) {
            synchronized (ExerciseRepository.class) {
                if (sInstance == null) {
                    sInstance = new RoutineWithDexersRepository(application);
                }
            }
        }
        return sInstance;
    }

    // 루틴들을 얻습니다.
    public LiveData<List<RoutineWithDexers>> getRoutines() {
        return routineWithDexersDao.getRoutinesWithDexers();
    }

    // 해당하는 루틴을 얻습니다. by routineId
    public LiveData<RoutineWithDexers> getRoutinesById(int rId) {
        return routineWithDexersDao.getRoutineWithDexers(rId);
    }

    public LiveData<List<RoutineWithDexers>> getRoutinesByCategory(int category) {
        return routineWithDexersDao.getRoutinesByCategory(category);
    }

}

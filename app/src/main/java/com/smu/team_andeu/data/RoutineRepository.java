package com.smu.team_andeu.data;

import android.app.Application;
import android.database.Cursor;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RoutineRepository {

    private static RoutineRepository sInstance;
    private final RoutineDao mRoutineDao;



    public RoutineRepository(final Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mRoutineDao = db.routineDao();

    }

    public static RoutineRepository getInstance(final Application application) {
        if (sInstance == null) {
            synchronized (ExerciseRepository.class) {
                if (sInstance == null) {
                    sInstance = new RoutineRepository(application);
                }
            }
        }
        return sInstance;
    }

    public Cursor getRoutineName(int categoryId){ return mRoutineDao.loadRawRoutineByCategory(categoryId);}


}
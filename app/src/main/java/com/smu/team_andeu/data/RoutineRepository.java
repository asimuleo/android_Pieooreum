package com.smu.team_andeu.data;

import android.app.Application;
import android.database.Cursor;

import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public LiveData<Routine> getRoutine(int rId){return mRoutineDao.getRoutine(rId);}


    public void updateRoutineMaxorder(RoutineOrder routineOrder){
        final ExecutorService e = Executors.newSingleThreadExecutor();
        e.submit(() -> mRoutineDao.updateRutineMaxOrder(routineOrder));
        e.shutdown();
    }

    public void updateRoutineName(RoutineName routineName){
        final ExecutorService e = Executors.newSingleThreadExecutor();
        e.submit(() -> mRoutineDao.updateRutineName(routineName));
        e.shutdown();
    }

    public void removeRoutineById(int rId){
        final ExecutorService e = Executors.newSingleThreadExecutor();
        e.submit(() -> mRoutineDao.deleteRoutineById(rId));
        e.shutdown();
    }
}

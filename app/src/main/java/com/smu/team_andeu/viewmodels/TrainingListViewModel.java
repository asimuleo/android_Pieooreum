package com.smu.team_andeu.viewmodels;

import android.app.Application;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.smu.team_andeu.data.RoutineRepository;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TrainingListViewModel extends AndroidViewModel {

    static final ExecutorService e = Executors.newFixedThreadPool(3);

    private final RoutineRepository routineRepository;

    public TrainingListViewModel(@NonNull Application application) {
        super(application);
        routineRepository = RoutineRepository.getInstance(application);
    }

    public Cursor getCursor(int category) {
        Callable<Cursor> task = () -> routineRepository.getRoutineName(category);
        Future<Cursor> future = e.submit(task);
        try{
            return future.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        e.shutdown();
        return null;
    }
}

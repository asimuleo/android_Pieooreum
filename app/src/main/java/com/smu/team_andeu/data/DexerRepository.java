package com.smu.team_andeu.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DexerRepository {

    private static DexerRepository sInstance;
    private final DexerDao mDexerDao;

    public DexerRepository(final Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mDexerDao = db.dexerDao();
    }

    public static DexerRepository getInstance(final Application application) {
        if (sInstance == null) {
            synchronized (ExerciseRepository.class) {
                if (sInstance == null) {
                    sInstance = new DexerRepository(application);
                }
            }
        }
        return sInstance;
    }

    public void insert(Dexer dexer){
        final ExecutorService e = Executors.newSingleThreadExecutor();
        e.submit(() -> mDexerDao.insert(dexer));
        e.shutdown();
    }

    public LiveData<Dexer> getDexerById(int dexerId) {
        return mDexerDao.getDexer(dexerId);
    }

}

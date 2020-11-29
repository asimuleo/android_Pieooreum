package com.smu.team_andeu.data;

import android.app.Application;

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
        mDexerDao.insert(dexer);
    }
}

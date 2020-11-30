package com.smu.team_andeu.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.smu.team_andeu.data.Dexer;
import com.smu.team_andeu.data.DexerRepository;

public class DetailDexerViewModel extends AndroidViewModel {

    private final LiveData<Dexer> mObservableDexer;

    public DetailDexerViewModel(@NonNull Application application, DexerRepository repository,
                                final int dexerId) {
        super(application);
        mObservableDexer = repository.getDexerById(dexerId);

    }

    public LiveData<Dexer> getDexer(){return mObservableDexer;}

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mDexerId;

        private final DexerRepository mRepository;

        public Factory(@NonNull Application application, int dexerId) {
            mApplication = application;
            mDexerId = dexerId;
            mRepository = DexerRepository.getInstance(application);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new DetailDexerViewModel(mApplication, mRepository, mDexerId);
        }
    }
}

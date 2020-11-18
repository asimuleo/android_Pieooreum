package com.smu.team_andeu.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DexerRepository {
    private DexerDao mDexerDao;
    private LiveData<List<Dexer>> alldexers;
}

package com.smu.team_andeu.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface RoutineWithDexersDao {

    // 모든 튜플들을 다 반환합니다.
    @Transaction
    @Query("SELECT * FROM routine")
    LiveData<List<RoutineWithDexers>> getRoutinesWithDexers();


    // 해당하는 루틴의 튜플을 반환합니다.
    @Transaction
    @Query("SELECT * FROM routine WHERE routineId = :rid")
    LiveData<RoutineWithDexers> getRoutineWithDexer(int rid);

    // 카테고리는 0,1,2 존재합니다.
    @Transaction
    @Query("SELECT * FROM routine WHERE category = :category")
    LiveData<List<RoutineWithDexers>> getRoutinesByCategory(int category);

}

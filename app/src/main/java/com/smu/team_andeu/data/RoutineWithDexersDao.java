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
    @Query("SELECT * FROM routine_table")
    LiveData<List<RoutineWithDexers>> getRoutinesWithDexers();


    // 해당하는 루틴의 튜플을 반환합니다.
    @Transaction
    @Query("SELECT * FROM routine_table WHERE routineId = :rId")
    LiveData<RoutineWithDexers> getRoutineWithDexers(int rId);

    // 카테고리는 0,1,2 존재합니다.
    @Transaction
    @Query("SELECT * FROM routine_table WHERE category = :category")
    LiveData<List<RoutineWithDexers>> getRoutinesByCategory(int category);

}

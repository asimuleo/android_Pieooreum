package com.smu.team_andeu.data;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface RoutineDao {
    @Transaction
    @Query("SELECT * FROM Routine")
    List<RoutineWithDexers> getRoutinesWithDexers();

    @Transaction
    @Query("SELECT * FROM Routine WHERE routineId = :rid")
    RoutineWithDexers getRoutineWithDexers(int rid);
}

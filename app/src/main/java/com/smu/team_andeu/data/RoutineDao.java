package com.smu.team_andeu.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Routine routine);

    @Query("SELECT * FROM routine_table WHERE routineId = :rId")
    LiveData<Routine> getRoutine(int rId);
}

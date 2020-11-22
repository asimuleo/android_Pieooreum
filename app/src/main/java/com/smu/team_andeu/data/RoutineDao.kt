package com.smu.team_andeu.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(routine: Routine?)

    @Query("SELECT * FROM routine_table WHERE routineId = :rId")
    fun getRoutine(rId: Int): LiveData<Routine>

    // 초기 넣기 : 비동기식 처리중
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routines: List<Routine>)

}
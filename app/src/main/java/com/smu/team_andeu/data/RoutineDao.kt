package com.smu.team_andeu.data


import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoutineDao {

    @Query("SELECT routineId as _id, r_name FROM routine WHERE category = :categoryId")
    fun loadRawRoutineByCategory(categoryId: Int): Cursor //LiveData<Cursor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(routine: Routine?)

    @Query("SELECT * FROM routine WHERE routineId = :rId")
    fun getRoutine(rId: Int): LiveData<Routine>

    // 초기 넣기 : 비동기식 처리중
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routines: List<Routine>)

    @Update(entity = Routine::class)
    fun updateRutineMaxOrder(routine:RoutineOrder)
}
package com.smu.team_andeu.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Room DAO 를 사용하여 데이터 액세스
// https://developer.android.com/training/data-storage/room/accessing-data.html

interface ExerciseDao {
    // 이름 순으로 가져오기.
    @Query("SELECT * FROM exers ORDER BY name")
    fun getExers(): LiveData<List<Exercise>>

    // id로 찾기
    @Query("SELECT * FROM exers WHERE id = :exerId")
    fun getExer(exerId: String): LiveData<Exercise>

    // group으로 찾기
    @Query("SELECT * FROM exers WHERE 'group' = :group ORDER BY name")
    fun getExersWithGroup(group : String): LiveData<List<Exercise>>

    // 초기 넣기 : 비동기식 처리중
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Exercise>)

}
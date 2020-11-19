package com.smu.team_andeu.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface GroupExerCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(groupExerCrossRefDao: List<GroupExerCrossRef>)
}
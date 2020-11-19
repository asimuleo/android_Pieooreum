package com.smu.team_andeu.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface GroupWithExersDao {

    // 모든 튜플들을 다 반환합니다.
    @Transaction
    @Query("SELECT * FROM group_table")
    LiveData<List<GroupWithExers>> getGroupsWithExers();

    // 해당하는 그룹의 튜플을 반환합니다.
    @Transaction
    @Query("SELECT * FROM GROUP_TABLE WHERE groupId = :gId")
    LiveData<List<GroupWithExers>> getGroupWithExers(int gId);

}

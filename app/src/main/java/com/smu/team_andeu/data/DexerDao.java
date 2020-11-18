package com.smu.team_andeu.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DexerDao {

    // Dexer 객체를 통해 insert 하는 함수 입니다.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dexer dexer);

    // 위험 : 모든 Phomer의 루틴안의 운동들이 다사라질 수 있는 함수입니다.
    @Query("DELETE FROM dexer_table")
    void deleteAll();

    // Dexer의 Id로 부터 Dexer를 얻는 함수.
    @Query("SELECT * FROM dexer_table WHERE dexer_id = :dexer_id")
    LiveData<Dexer> getDexer(int dexer_id);

    // 가나다 순으로 모든 Dexer를 얻는 함수
    @Query("SELECT * FROM dexer_table  ORDER BY d_exer_name ASC")
    LiveData<List<Dexer>> getDexers();

}

package com.smu.team_andeu.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DexerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dexer dexer);

    @Query("DELETE FROM dexer_table")
    void deleteAll();

    @Query("SELECT * FROM dexer_table WHERE id = :dexer_id")
    LiveData<Dexer> getDexer(int dexer_id);

//    @Query("SELECT * FROM dexer_table ORDER BY ex ASC")
//    List<Dexer> getAlphabetizedWords();

    @Query("SELECT exers.name, dexer_table.* FROM dexer_table JOIN exers ON (dexer_table.id=exers.id) ORDER BY exers.name")
    LiveData<List<Dexer>> getDexers();
}

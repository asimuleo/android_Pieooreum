package com.smu.team_andeu.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RoutineWithDexers {
    @Embedded
    public Routine routine;
    @Relation(
            parentColumn = "routineId",
            entityColumn = "dexer_id"
    )
    public List<Routine> dexerlists;
}


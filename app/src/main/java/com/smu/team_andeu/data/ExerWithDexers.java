package com.smu.team_andeu.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ExerWithDexers {
    @Embedded private Exercise exercise;
    @Relation(
            parentColumn = "exerId",
            entityColumn = "dexer_id"
    )
    private List<Dexer> dexers;
}

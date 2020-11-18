package com.smu.team_andeu.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RoutineWithDexers {
    @Embedded
    private Routine routine;
    @Relation(
            parentColumn = "routineId",
            entityColumn = "d_routineId"
    )
    private List<Dexer> dexers;

    public RoutineWithDexers(Routine routine, List<Dexer> dexers) {
        this.routine = routine;
        this.dexers = dexers;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public List<Dexer> getDexers() {
        return dexers;
    }

    public void setDexers(List<Dexer> dexers) {
        this.dexers = dexers;
    }
}


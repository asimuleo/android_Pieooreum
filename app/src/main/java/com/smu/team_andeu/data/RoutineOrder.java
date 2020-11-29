package com.smu.team_andeu.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoutineOrder {
    @PrimaryKey
    int routineId;
    int maxOrder;

    public RoutineOrder(int routineId, int maxOrder) {
        this.routineId = routineId;
        this.maxOrder = maxOrder;
    }
}

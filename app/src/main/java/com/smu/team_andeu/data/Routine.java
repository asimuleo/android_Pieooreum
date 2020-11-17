package com.smu.team_andeu.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "routine")
public class Routine {
    @PrimaryKey private String name;
    private double totalCal;
    private int totalTime;
    private String summary;
}

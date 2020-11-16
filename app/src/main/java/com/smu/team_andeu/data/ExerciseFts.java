package com.smu.team_andeu.data;


import androidx.room.Entity;
import androidx.room.Fts4;

@Entity(tableName = "exersFts")
@Fts4(contentEntity = Exercise.class)
public class ExerciseFts {
    private String name;
    private String description;

    public ExerciseFts(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

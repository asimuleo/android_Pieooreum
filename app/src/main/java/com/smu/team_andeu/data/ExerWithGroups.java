package com.smu.team_andeu.data;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ExerWithGroups {
    @Embedded
    private Exercise exercise;
    @Relation(
            parentColumn = "exerId",
            entityColumn = "groupId",
            associateBy = @Junction(GroupExerCrossRef.class)
    )
    private List<Group> groups;

    public ExerWithGroups(Exercise exercise, List<Group> groups) {
        this.exercise = exercise;
        this.groups = groups;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}

package com.smu.team_andeu.data;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class GroupWithExers {
    @Embedded
    private Group group;
    @Relation(
            parentColumn = "groupId",
            entityColumn = "exerId",
            associateBy = @Junction(GroupExerCrossRef.class)
    )
    private List<Exercise> exers;

    public GroupWithExers(Group group, List<Exercise> exers) {
        this.group = group;
        this.exers = exers;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Exercise> getExers() {
        return exers;
    }

    public void setExers(List<Exercise> exers) {
        this.exers = exers;
    }
}

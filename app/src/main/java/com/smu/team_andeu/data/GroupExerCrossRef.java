package com.smu.team_andeu.data;


import androidx.room.Entity;


@Entity(primaryKeys = {"groupId", "exerId"})
public class GroupExerCrossRef {

    private int groupId;
    private int exerId;

    public GroupExerCrossRef(int groupId, int exerId) {
        this.groupId = groupId;
        this.exerId = exerId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getExerId() {
        return exerId;
    }

    public void setExerId(int exerId) {
        this.exerId = exerId;
    }
}

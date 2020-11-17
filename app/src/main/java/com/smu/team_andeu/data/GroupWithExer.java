package com.smu.team_andeu.data;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

public class GroupWithExer {

    @Embedded private Group group;

    private int exerId;
    private int groupId;

    public int getExerId() {
        return exerId;
    }

    public void setExerId(int exerId) {
        this.exerId = exerId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}

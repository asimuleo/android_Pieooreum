package com.smu.team_andeu.data;


import androidx.room.Entity;

@Entity(tableName = "exerGroup", primaryKeys = {"exers.id, group.id"})
public class ExerGroup {
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

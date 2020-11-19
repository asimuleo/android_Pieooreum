package com.smu.team_andeu.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "group_table")
public class Group {

    @PrimaryKey
    private int groupId;

    private String g_name;

    public Group(int groupId, String g_name) {
        this.groupId = groupId;
        this.g_name = g_name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }
}

package com.smu.team_andeu.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.smu.team_andeu.model.Exer;

// Room Entity 를 사용하여 데이터 정의
// https://developer.android.com/training/data-storage/room/defining-data.html

@Entity(tableName = "exers")
public class Exercise implements Exer { // Entity
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int exerId;
    private String name;
    private double calorie;
    private String description;
    private int time;
    @ColumnInfo(name = "group")
    private String group;
    private String imageUrl;

    public boolean isTime(){return time > 0;}

    public int getExerId() {
        return exerId;
    }

    public String getName() {
        return name;
    }

    public double getCalorie() {
        return calorie;
    }

    public String getDescription() {
        return description;
    }

    public int getTime() {
        return time;
    }

    public String getGroup() {
        return group;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

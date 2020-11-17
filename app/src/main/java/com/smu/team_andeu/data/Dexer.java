package com.smu.team_andeu.data;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.smu.team_andeu.model.Exer;

@Entity
public class Dexer{
    @PrimaryKey
    public int dexer_id;

    public String nicname;
    public int count;
    public int time;
    public int calories;

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setnicName(String nicname) {
        this.nicname = nicname;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return nicname;
    }

    public int getCount() {
        return count;
    }

    public int getTime() {
        return time;
    }

    @ColumnInfo(name = "categories") public int categories;

    @Embedded
    public Exer exer;
}

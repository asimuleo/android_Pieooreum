package com.smu.team_andeu.data;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.smu.team_andeu.model.Exer;

@Entity(tableName = "dexer_table")
public class Dexer{
    @PrimaryKey
    public int dexer_id;

    public String nicname;
    public int count;
    public int dtime;
    public int calories;

    public int getDexer_id() {
        return dexer_id;
    }

    public void setDexer_id(int dexer_id) {
        this.dexer_id = dexer_id;
    }

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

    public void setTime(int dtime) {
        this.dtime = dtime;
    }

    public String getName() {
        return nicname;
    }

    public int getCount() {
        return count;
    }

    public int getTime() {
        return dtime;
    }

    public Exercise getDExer() {
        return exer;
    }

    public void setDExer(Exercise exer) {
        this.exer = exer;
    }

    @ColumnInfo(name = "categories") public int categories;


    @Embedded
    public Exercise exer;
}

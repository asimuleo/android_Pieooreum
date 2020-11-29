package com.smu.team_andeu.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dexer_table")
public class Dexer implements com.smu.team_andeu.model.Dexer {
    @PrimaryKey
    private int dexer_id;
    // 내가 속한 Exer
    private int exerOwnerId;
    // 내가 속한 Routine
    private int d_routineId;

    private String d_nicname;
    private int d_count;
    private int d_time;
    private int d_calories;
    private int d_categories;

    public Dexer(int dexer_id, int exerOwnerId, String d_nicname, int d_count, int d_time, int d_calories, int d_routineId, int d_categories) {
        this.dexer_id = dexer_id;
        this.exerOwnerId = exerOwnerId;
        this.d_nicname = d_nicname;
        this.d_count = d_count;
        this.d_time = d_time;
        this.d_calories = d_calories;
        this.d_routineId = d_routineId;
        this.d_categories = d_categories;
    }

    public int getDexer_id() {
        return dexer_id;
    }

    public void setDexer_id(int dexer_id) {
        this.dexer_id = dexer_id;
    }

    public int getExerOwnerId() {
        return exerOwnerId;
    }

    public void setExerOwnerId(int exerOwnerId) {
        this.exerOwnerId = exerOwnerId;
    }

    public String getD_nicname() {
        return d_nicname;
    }

    public void setD_nicname(String d_nicname) {
        this.d_nicname = d_nicname;
    }

    public int getD_count() {
        return d_count;
    }

    public void setD_count(int d_count) {
        this.d_count = d_count;
    }

    public int getD_time() {
        return d_time;
    }

    public void setD_time(int d_time) {
        this.d_time = d_time;
    }

    public int getD_calories() {
        return d_calories;
    }

    public void setD_calories(int d_calories) {
        this.d_calories = d_calories;
    }

    public int getD_routineId() {
        return d_routineId;
    }

    public void setD_routineId(int d_routineId) {
        this.d_routineId = d_routineId;
    }

    public int getD_categories() {
        return d_categories;
    }

    public void setD_categories(int d_categories) {
        this.d_categories = d_categories;
    }

    @Override
    public int getDexerId() {
        return getDexer_id();
    }

    @Override
    public String getName() {
        return getD_nicname();
    }

    @Override
    public double getCalorie() {
        return getD_calories();
    }

    @Override
    public int getCount() {
        return getD_count();
    }

    @Override
    public int getTime() {
        return getD_time();
    }

    @Override
    public int getCategories() {
        return getD_categories();
    }

    @Override
    public int getExerId() {
        return getExerOwnerId();
    }
}

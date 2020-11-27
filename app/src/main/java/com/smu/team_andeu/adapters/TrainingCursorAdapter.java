package com.smu.team_andeu.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.smu.team_andeu.R;

public class TrainingCursorAdapter extends CursorAdapter {

    private static final String TAG = "TrainingCursorAdapter";

    LayoutInflater inflater;
    Cursor c;


    public TrainingCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        inflater = LayoutInflater.from(context);
        this.c = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_dropdown_item_1line
                , null, true);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(android.R.id.text1)).setText(cursor.getString(1));
        Log.e(TAG, cursor.getString(cursor.getColumnIndex("r_name")));

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View v = inflater.inflate(android.R.layout.simple_dropdown_item_1line
                , parent, false);

        ((TextView) v.findViewById(android.R.id.text1)).setText(c.getString(1));
        return v;
    }
}

package com.smu.team_andeu.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.smu.team_andeu.R;
import com.smu.team_andeu.callback.RoutineClickCallback;
import com.smu.team_andeu.data.RoutineWithDexers;
import com.smu.team_andeu.databinding.ItemRoutineBinding;

import java.util.List;

public class RoutineListAdapter extends RecyclerView.Adapter<RoutineListAdapter.RoutineViewHolder> {

    List<? extends RoutineWithDexers> mRoutineList;

    @NonNull
    private final RoutineClickCallback mRoutineClickCallback;

    public RoutineListAdapter(@NonNull RoutineClickCallback clickCallback) {
        mRoutineClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setRoutineList(final List<? extends RoutineWithDexers> routineList) {
        if(mRoutineList == null) {
            mRoutineList = routineList;
            notifyItemRangeInserted(0, routineList.size());
        }else{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mRoutineList.size();
                }

                @Override
                public int getNewListSize() {
                    return routineList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mRoutineList.get(oldItemPosition).getRoutine().getRoutineId() ==
                            routineList.get(newItemPosition).getRoutine().getRoutineId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    RoutineWithDexers newR = routineList.get(newItemPosition);
                    RoutineWithDexers oldR = mRoutineList.get(oldItemPosition);
                    return newR.getRoutine().getRoutineId() == oldR.getRoutine().getRoutineId();
                }
            });
            mRoutineList = routineList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRoutineBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_routine,
                        parent, false);
        binding.setCallback(mRoutineClickCallback);
        return new RoutineViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        holder.binding.setRoutine(mRoutineList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mRoutineList == null ? 0 : mRoutineList.size();
    }

    static class RoutineViewHolder extends RecyclerView.ViewHolder {

        final ItemRoutineBinding binding;

        public RoutineViewHolder(@NonNull ItemRoutineBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

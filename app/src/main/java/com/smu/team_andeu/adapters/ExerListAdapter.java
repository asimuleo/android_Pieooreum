package com.smu.team_andeu.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.smu.team_andeu.R;
import com.smu.team_andeu.callback.ExerClickCallback;
import com.smu.team_andeu.databinding.ItemExerBinding;
import com.smu.team_andeu.model.Exer;

import java.util.List;

public class ExerListAdapter extends RecyclerView.Adapter<ExerListAdapter.ExerViewHolder> {

    List<? extends Exer> mExerList;

    @NonNull
    private final ExerClickCallback mExerClickCallback;


    public ExerListAdapter(@NonNull ExerClickCallback clickCallback) {
        mExerClickCallback = clickCallback;
        setHasStableIds(true);
    }


    public void setExerList(final List<? extends Exer> exerList) {
        if (mExerList == null) {
            mExerList = exerList;
            notifyItemRangeInserted(0, exerList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mExerList.size();
                }

                @Override
                public int getNewListSize() {
                    return exerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mExerList.get(oldItemPosition).getExerId() ==
                            exerList.get(newItemPosition).getExerId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Exer newExer = exerList.get(newItemPosition);
                    Exer oldExer = mExerList.get(oldItemPosition);
                    return newExer.getExerId() == oldExer.getExerId()
                            && TextUtils.equals(newExer.getDescription(), oldExer.getDescription())
                            && TextUtils.equals(newExer.getName(), oldExer.getName())
                            && newExer.getCalorie() == oldExer.getCalorie();
                }
            });
            // 이제 바꾸기
            mExerList = exerList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public ExerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 바인딩 유틸을 통해 inflate한 후 뷰홀더를 생성
        ItemExerBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_exer,
                        parent, false);
        binding.setCallback(mExerClickCallback);
        return new ExerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerViewHolder holder, int position) {
        holder.binding.setExer(mExerList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mExerList == null ? 0 : mExerList.size();
    }

    @Override
    public long getItemId(int position) {
        return mExerList.get(position).getExerId();
    }

    static class ExerViewHolder extends RecyclerView.ViewHolder {

        final ItemExerBinding binding;

        public ExerViewHolder(@NonNull ItemExerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

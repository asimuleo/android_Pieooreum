package com.smu.team_andeu.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.smu.team_andeu.R;
import com.smu.team_andeu.callback.DexerClickCallback;
import com.smu.team_andeu.model.Dexer;
import com.smu.team_andeu.databinding.ItemDexerBinding;

import java.util.List;

public class DexerListAdapter extends RecyclerView.Adapter<DexerListAdapter.DexerViewHolder>{

    List<? extends Dexer> mDexerList;

    @NonNull
    private final DexerClickCallback mDexerClickCallback;


    public DexerListAdapter(@NonNull DexerClickCallback mDexerClickCallback) {
        this.mDexerClickCallback = mDexerClickCallback;
        setHasStableIds(true);
    }

    public void setDexerList(final List<? extends Dexer> dexerList) {
        if (mDexerList == null) {
            mDexerList = dexerList;
            notifyItemRangeInserted(0, dexerList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mDexerList.size();
                }

                @Override
                public int getNewListSize() {
                    return dexerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mDexerList.get(oldItemPosition).getDexerId()==
                            dexerList.get(newItemPosition).getDexerId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Dexer newDexer = dexerList.get(newItemPosition);
                    Dexer oldDexer = mDexerList.get(oldItemPosition);
                    return newDexer.getDexerId() == oldDexer.getDexerId()
                            && TextUtils.equals(newDexer.getName(), oldDexer.getName())
                            && newDexer.getTime() == oldDexer.getTime()
                            && newDexer.getCount() == oldDexer.getCount();
                }
            });
            // 이제 바꾸기
            mDexerList = dexerList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public DexerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDexerBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_dexer,
                        parent, false);
        binding.setCallback(mDexerClickCallback);
        return new DexerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DexerViewHolder holder, int position) {
        holder.binding.setDexer(mDexerList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDexerList == null ? 0 : mDexerList.size();
    }

    @Override
    public long getItemId(int position) {
        return mDexerList.get(position).getDexerId();
    }

    static class DexerViewHolder extends RecyclerView.ViewHolder {

        final ItemDexerBinding binding;

        public DexerViewHolder(@NonNull ItemDexerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

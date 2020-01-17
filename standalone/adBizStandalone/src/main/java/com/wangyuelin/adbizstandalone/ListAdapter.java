package com.wangyuelin.adbizstandalone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : yuelinwang
 * time   : 2020-01-17 16:02
 * desc   :
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {
    private List<String> mData;

    public void setData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        String name = mData.get(position);
        holder.tvName.setText(name);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

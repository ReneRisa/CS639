package com.example.myfirebaseactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public ListAdapter(Context context, LinkedList<String> wordList){
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        public final TextView wordItemView;
        final ListAdapter mAdapter;
        public ListViewHolder(View itemView, ListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.employee);
            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.employeelist_item, parent,false);
        return new ListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}

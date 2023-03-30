package com.example.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final ArrayList<String> mWordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, ArrayList<String> wordList){
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        public WordViewHolder(View itemView, WordListAdapter adapter){
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Log.i("LOGTAG", mInflater.getContext().getClass().getName());
            Log.i("LOGTAG", mInflater.getContext().getClass().getSimpleName().toString().toLowerCase());
            if(mInflater.getContext().getClass().getSimpleName().toString().toLowerCase().equals("secondactivity")){
                // Get the position of the item that was clicked.
                int mPosition = getLayoutPosition();
                // Use that to access the affected item in mWordList.
                String element = mWordList.get(mPosition);
                // Change the word in the mWordList.
                mWordList.set(mPosition, " " + element);
                // Notify the adapter that the data has changed so it can
                // update the RecyclerView to display the data.
                mAdapter.notifyDataSetChanged();
            }else {
                // Get the position of the item that was clicked.
                int mPosition = getLayoutPosition();
                // Use that to access the affected item in mWordList.
                String element = mWordList.get(mPosition);
                // Change the word in the mWordList.
                mWordList.set(mPosition, "Clicked! " + element);
                // Notify the adapter that the data has changed so it can
                // update the RecyclerView to display the data.
                mAdapter.notifyDataSetChanged();
            }


        }
    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }



}

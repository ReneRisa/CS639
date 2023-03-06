package com.example.whowroteitloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
//import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {
    private String mQueryString;

    BookLoader(@NonNull Context context, String queryString){
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}

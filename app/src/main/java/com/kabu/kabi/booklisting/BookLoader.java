package com.kabu.kabi.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mStringUrl;

    public BookLoader(@NonNull Context context, String bookQueryUrl) {
        super(context);
        mStringUrl = bookQueryUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        if (mStringUrl == null) { return null; }
        return QueryUtils.fetchBookData(mStringUrl);
    }
}

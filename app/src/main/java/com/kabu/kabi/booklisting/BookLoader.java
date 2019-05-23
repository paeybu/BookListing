package com.kabu.kabi.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mStringUrl;

    public BookLoader(@NonNull Context context, String bookQueryUrl) {
        super(context);
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        return null;
    }
}

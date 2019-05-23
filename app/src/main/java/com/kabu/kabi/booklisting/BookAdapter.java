package com.kabu.kabi.booklisting;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@androidx.annotation.NonNull Context context, int resource, @androidx.annotation.NonNull List<Book> objects) {
        super(context, resource, objects);
    }
}

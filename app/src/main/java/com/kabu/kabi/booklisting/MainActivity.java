package com.kabu.kabi.booklisting;

import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    private final static String BOOK_QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";
    private final static String AUTHORITY = "www.googleapis.com";
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);
        getLoaderManager().initLoader(0, null, this);

        mAdapter = new BookAdapter(getApplicationContext(), R.layout.list_item, new ArrayList<Book>());
        mListView.setAdapter(mAdapter);

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(getApplicationContext(), BOOK_QUERY_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}

package com.kabu.kabi.booklisting;

import android.content.Loader;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    private final static String BOOK_QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";
    private final static String AUTHORITY = "www.googleapis.com";
    private final static String MAX_RESULTS = "maxResults";
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);
        mAdapter = new BookAdapter(getApplicationContext(), R.layout.list_item, new ArrayList<Book>());
        Button searchButton = findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoaderManager().getLoader(0) == null) {
                    getLoaderManager().initLoader(0, null, MainActivity.this);
                } else {
                    getLoaderManager().restartLoader(0, null, MainActivity.this);
                }
            }
        });

        mListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        EditText editText = findViewById(R.id.bookEt);
        String query = editText.getText().toString();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(AUTHORITY)
                .appendPath("books")
                .appendPath("v1")
                .appendPath("volumes")
                .appendQueryParameter("q", query)
                .appendQueryParameter(MAX_RESULTS, "5")
                .fragment("section-name");
        String myUrl = builder.build().toString();
        return new BookLoader(getApplicationContext(), myUrl);
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

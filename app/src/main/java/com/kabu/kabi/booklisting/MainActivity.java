package com.kabu.kabi.booklisting;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static String BOOK_QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";
    BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    Sample books data
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Hello Android"));
        books.add(new Book("How to be a Billionaire"));
        books.add(new Book("Welcome to the three commas club"));


        BookAdapter mAdapter = new BookAdapter(this, R.layout.list_item, books);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(mAdapter);

    }


    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new BookLoader(this, BOOK_QUERY_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
        mAdapter.clear();
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}

package com.kabu.kabi.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String BOOK_QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    Sample books data
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Hello Android"));
        books.add(new Book("How to be a Billionaire"));
        books.add(new Book("Welcome to the three commas club"));

    }


}

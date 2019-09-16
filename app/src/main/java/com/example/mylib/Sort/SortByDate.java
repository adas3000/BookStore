package com.example.mylib.Sort;

import com.example.mylib.Data.Book;

import java.util.Comparator;

public class SortByDate implements Comparator<Book> {
    @Override
    public int compare(Book book, Book t1) {
        return  book.getFinish_date().compareTo(t1.getFinish_date());
    }
}

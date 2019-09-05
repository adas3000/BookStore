package com.example.mylib.Data;


import android.annotation.TargetApi;

import java.sql.Date;
import java.util.Objects;

@TargetApi(19)
public class Book {


    public static class Builder {

        private final String title;
        private final String author;
        private final String short_description;
        private final String img_url;

        private Date date = null;
        private int book_reading_state = -1;
        private boolean user_has_book = false;
        private boolean book_is_favorite = false;
        private int mark = 0;

        public Builder(String title, String author, String short_description, String img_url) {
            this.title = title;
            this.author = author;
            this.short_description = short_description;
            this.img_url = img_url;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder book_Reading_State(int i){
            this.book_reading_state = i;
            return this;
        }

        public Builder user_Has_Book(boolean b){
            this.user_has_book = b;
            return this;
        }

        public Builder book_Is_Favorite(boolean b){
            this.book_is_favorite = b;
            return this;
        }

        public Builder Mark(int mark){
            this.mark = mark;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

    }


    private String title, author, short_description, image_url;
    private Date finish_date;
    private int book_reading_state ;
    private boolean user_has_book;
    private boolean book_is_favorite;



    private int mark;

    private Book(Builder builder) {
        title = builder.title;
        author = builder.author;
        short_description = builder.short_description;
        image_url = builder.img_url;
        finish_date = builder.date;
        book_reading_state = builder.book_reading_state;
        user_has_book = builder.user_has_book;
        book_is_favorite = builder.book_is_favorite;
        mark = builder.mark;
    }


    public Date getFinish_date() {
        return finish_date;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getImage_url() {
        return image_url;
    }

    public boolean isBook_is_favorite() { return book_is_favorite; }

    public boolean isUser_has_book() { return user_has_book; }

    public int getBook_reading_state() { return book_reading_state; }

    public int getMark() { return mark; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_reading_state == book.book_reading_state &&
                user_has_book == book.user_has_book &&
                book_is_favorite == book.book_is_favorite &&
                mark == book.mark &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(short_description, book.short_description) &&
                Objects.equals(image_url, book.image_url) &&
                Objects.equals(finish_date, book.finish_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, short_description, image_url, finish_date, book_reading_state, user_has_book, book_is_favorite, mark);
    }
}
package com.example.mylib.Data;


import android.annotation.TargetApi;

import java.util.Objects;

public class Book {

    public String getFinish_date() {
        if(!readenByUser) return "";


        StringBuilder sb = new StringBuilder(8);
        sb.append(finish_date.day);
        sb.append("-");
        sb.append(finish_date.month);
        sb.append("-");
        sb.append(finish_date.year);

        if(finish_date.day==0 || finish_date.month ==0 || finish_date.year ==0)
            return "";


        return sb.toString();
    }

    static class Date{
        public Date(int y,int m,int d){
            year = y;
            month = m;
            day = d;
        }
        public int year,month,day;
    }

    public static class Builder{

        private final String title;
        private final String author;
        private final String short_description;
        private final String img_url;

        private Date date = new Date(0,0,0);
        private boolean readenByUser = false;

        public Builder(String title,String author,String short_description,String img_url){
            this.title = title;
            this.author = author;
            this.short_description = short_description;
            this.img_url = img_url;
        }
        public Builder date(int y,int m,int d){this.date = new Date(y,m,d);return this;}
        public Builder readenByUser(boolean b){readenByUser = b;return this;}

        public Book build(){
            return new Book(this);
        }


    }



   private String title,author,short_description,image_url;
   private boolean readenByUser;
   private Date finish_date;

    public Book(String title,String author,String short_description,String image_url,
                boolean readenByUser,int y,int m,int d){
        this.title = title;
        this.author = author;
        this.short_description = short_description;
        this.image_url = image_url;
        this.readenByUser = readenByUser;
    }

    private Book(Builder builder){
        title = builder.title;
        author = builder.author;
        short_description = builder.short_description;
        image_url = builder.img_url;
        finish_date = builder.date;
        readenByUser = builder.readenByUser;
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

    public boolean isReadenByUser() {
        return readenByUser;
    }

    @TargetApi(19)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return readenByUser == book.readenByUser &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(short_description, book.short_description) &&
                Objects.equals(image_url, book.image_url) &&
                Objects.equals(finish_date, book.finish_date);
    }
    @TargetApi(19)
    @Override
    public int hashCode() {
        return Objects.hash(title, author, short_description, image_url, readenByUser, finish_date);
    }
}

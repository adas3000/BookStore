package com.example.mylib.Data;

import android.graphics.Bitmap;

public class Book {

    public Date getFinish_date() {
        return finish_date;
    }

    public class Date{
        public Date(int y,int m,int d){
            year = y;
            month = m;
            day = d;
        }
        public int year,month,day;
    }


   private String title,author,short_description;
   private Bitmap img;
   private boolean readenByUser;
   private Date finish_date;

    public Book(String title,String author,String short_description,Bitmap img,
                boolean readenByUser,int y,int m,int d){
        this.title = title;
        this.author = author;
        this.short_description = short_description;
        this.img = img;
        this.readenByUser = readenByUser;

        if(!this.readenByUser)
            finish_date = new Date(0,0,0);
        else
        finish_date = new Date(y,m,d);
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

    public Bitmap getImage_url() {
        return img;
    }

    public boolean isReadenByUser() {
        return readenByUser;
    }
}

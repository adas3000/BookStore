package com.example.mylib.Data;

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

    public String getImage_url() {
        return image_url;
    }

    public boolean isReadenByUser() {
        return readenByUser;
    }
}

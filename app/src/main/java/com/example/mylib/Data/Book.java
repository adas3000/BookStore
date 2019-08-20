package com.example.mylib.Data;

public class Book {

    public Date getFinish_date() {
        return finish_date;
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
}

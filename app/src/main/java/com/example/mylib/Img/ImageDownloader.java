package com.example.mylib.Img;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {

    private ImageView bmImage;
    private Bitmap bitmap;
    private String url="https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";

    public ImageDownloader(ImageView bmImage,Bitmap bitmap) {
        this.bmImage = bmImage;
        this.bitmap = bitmap;
    }

    public String getUrl(){return url;}


    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        this.url = url;
        bitmap = null;
        try{
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }catch(Exception e){
            e.fillInStackTrace();
        }
        return bitmap;
    }

    protected  void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }

}

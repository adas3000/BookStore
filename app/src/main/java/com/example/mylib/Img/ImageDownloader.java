package com.example.mylib.Img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.InputStream;


public class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

    private ImageView imgV;
    private Bitmap bitmap;

    public ImageDownloader(ImageView imageView,Bitmap bitmap){
        this.imgV = imageView;
        this.bitmap = bitmap;
    }


    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        bitmap = null;
        try{
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }catch(Exception e){
            e.fillInStackTrace();
        }
        return bitmap;
    }


    @Override
    protected  void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        imgV.setImageBitmap(bitmap);
    }



}
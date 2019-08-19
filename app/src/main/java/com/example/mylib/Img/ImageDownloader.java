package com.example.mylib.Img;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {

    private ImageView bmImage;


    public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
    }


    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap mIcon = null;
        try{
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        }
        catch(Exception e){
            e.fillInStackTrace();
        }
        return mIcon;
    }

    protected  void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }

}

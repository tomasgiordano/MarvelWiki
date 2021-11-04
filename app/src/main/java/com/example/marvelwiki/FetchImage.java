package com.example.marvelwiki;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;

public class FetchImage extends Thread{
    String URL;
    Bitmap bitmap;
    Handler handler;
    Context context;
    CharacterViewHolder holder;

    FetchImage(String URL, Handler handler, Context context,CharacterViewHolder holder) {
        this.URL = URL;
        this.handler = handler;
        this.context = context;
        this.holder = holder;
    }

    @Override
    public void run(){

        InputStream inputStream = null;
        try {
            inputStream = new java.net.URL(URL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                holder.characterImage.setImageBitmap(bitmap);
            }
        });
    }
}

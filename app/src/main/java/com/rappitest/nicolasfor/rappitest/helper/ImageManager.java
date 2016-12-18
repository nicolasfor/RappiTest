package com.rappitest.nicolasfor.rappitest.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class ImageManager extends AsyncTask<String, Void, ArrayList<String>> {

    public ImageManager() {
    }

    protected ArrayList<String> doInBackground(String... urls) {
        String banner = urls[0];
        String icon = urls[1];
        String header = urls[2];
        ArrayList<String> array = new ArrayList<>();
        String mIcon11="";
        String mIcon12="";
        String mIcon13="";
            try {
                if(banner!=null && !banner.isEmpty() && !banner.equals("null")) {
                    InputStream in = new java.net.URL(banner).openStream();
                    mIcon11 = BitMapToString(BitmapFactory.decodeStream(in));
                    in.close();
                }
                if(icon!=null && !icon.isEmpty() && !icon.equals("null")) {
                    InputStream in2 =  new java.net.URL(icon).openStream();
                    mIcon12 = BitMapToString(BitmapFactory.decodeStream(in2));
                    in2.close();
                }
                if(header!=null && !header.isEmpty() && !header.equals("null")) {
                    InputStream in3 =  new java.net.URL(header).openStream();
                    mIcon13 = BitMapToString(BitmapFactory.decodeStream(in3));
                    in3.close();
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

        array.add(mIcon11);
        array.add(mIcon12);
        array.add(mIcon13);
        return array;
    }


   public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    protected void onPostExecute(ArrayList<String> result) {
        super.onPostExecute(result);
    }
}

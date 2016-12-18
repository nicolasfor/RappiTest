package com.rappitest.nicolasfor.rappitest.helper;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class HTTP extends AsyncTask<String, String, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public HTTP(){
    }


    @Override
    protected String doInBackground(String... str) {

        HttpURLConnection urlConnection=null;
        StringBuilder result = new StringBuilder();
        try{
            URL url = new URL(str[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            in.close();
            urlConnection.disconnect();
            return result.toString();

        }catch(IOException e){
            return null;
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }


}

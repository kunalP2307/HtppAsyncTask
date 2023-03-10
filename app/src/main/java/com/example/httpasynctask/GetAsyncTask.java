package com.example.httpasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAsyncTask extends AsyncTask<Void, Void, String> {

    private MainActivity activity;
    private AsyncCallBack asyncCallBack;
    private ProgressBar progressBar;

    GetAsyncTask setInstance(Context context, ProgressBar progressBar){
        this.activity = (MainActivity) context;
        asyncCallBack = (AsyncCallBack) context;
        this.progressBar = progressBar;
        return this;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = "";
        HttpURLConnection urlConnection = null;
        String string_url = "https://script.google.com/macros/s/AKfycbzCqyqIr9JFiwUzfZsOwMpTCKqVXoWNPAn6eUQXhpxtrgegS4UyXcLKgwvz6l-sIAtQ/exec?action=get_all";
        try {
            URL url = new URL(string_url);
            urlConnection = (HttpURLConnection) url.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode != 200){
                throw new IOException("Invalid Response from Server Status Code : "+statusCode);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            Log.d("", "doInBackground: "+result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Log.d("TAG", "onPostExecute: "+result);
        asyncCallBack.setResult(result);
    }
}

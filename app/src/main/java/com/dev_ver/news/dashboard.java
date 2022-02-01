package com.dev_ver.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    public static class BackgroundData extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String result="";
           try {
               URL url=new URL(strings[0]);
               HttpURLConnection urlConnection=null;
               urlConnection=(HttpURLConnection) url.openConnection();
               urlConnection.setRequestProperty("X-ACCESS-KEY","pub_3952d73247ec7d2b99b7609afd94e79c6ed6");
               InputStream input=urlConnection.getInputStream();
               InputStreamReader reader=new InputStreamReader(input);
               int data=reader.read();
                while(data!=-1)
                { char currentChar=(char)data;
                result+=currentChar;
                data=reader.read();
                }
               return result;
           }
           catch (Exception e)
           {
               e.printStackTrace();
               return "Failed Process";
           }
        }
    }

    public static class BackgroundImage extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap image;

            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection;
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                image= BitmapFactory.decodeStream(inputStream);
                return image;
              }

              catch(Exception e){
                e.printStackTrace();
                return null;
              }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ArrayList<NewsComp.NewsComponent> newsArray = new ArrayList<NewsComp.NewsComponent>();
        BackgroundData dataRead=new BackgroundData();
        try {
            String data = dataRead.execute("https://newsdata.io/api/1/news?&q=india").get();
            JSONObject data_object = new JSONObject(data);
            Log.i("data recieved is",data);
                JSONArray array= data_object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject newsJSONObject=array.getJSONObject(i);
                    NewsComp.NewsComponent news=new NewsComp.NewsComponent(
                    newsJSONObject.getString("title"),
                    newsJSONObject.getString("pubDate"),
                    newsJSONObject.getString("link"),
                    newsJSONObject.getString("image_url"),
                    newsJSONObject.getString("description"));
                            newsArray.add(news);
                }
            }
         catch(Exception e)
         {
             e.printStackTrace();
         }
        RecyclerView listShow=findViewById(R.id.listShow);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        listShow.setLayoutManager(layoutManager);

        NewsListAdapter adapter=new NewsListAdapter(newsArray);
        listShow.setAdapter(adapter);
        //adapter.updateNews(newsArray);

}
}
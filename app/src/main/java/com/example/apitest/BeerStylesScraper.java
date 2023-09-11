package com.example.apitest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

public class BeerStylesScraper {
    ArrayList<String> beerStyles;

    private OnBeerStylesFetchedListener listener;

    public void setOnBeerStylesFetchedListener(OnBeerStylesFetchedListener listener) {
        this.listener = listener;
    }


    @SuppressLint("StaticFieldLeak")
    public ArrayList<String> beerStyleScraper() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    // Fetch HTML content
                    Document doc = Jsoup.connect("https://www.craftbeer.com/beer-styles").get();
                    Elements styleWrappers = doc.select(".style-wrapper");
                     beerStyles = new ArrayList<>();

                    // Iterate through style wrappers and extract information
                    for (Element styleWrapper : styleWrappers) {
                        String styleName = styleWrapper.select(".caption-title").text();
                        beerStyles.add(styleName);
//                        Log.d("BeerStylesScraper", "Style Name: " + styleName);
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }

                if (listener != null) {
                    listener.onBeerStylesFetched(beerStyles);
                }


                return null;
            }
        }.execute();

        return beerStyles;
    }

    public interface OnBeerStylesFetchedListener {
        void onBeerStylesFetched(ArrayList<String> beerStyles);
    }

}

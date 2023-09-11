package com.example.apitest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements  BeerStylesScraper.OnBeerStylesFetchedListener{

    String style;
    WebView webView;

    String mostSimilarStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        style = intent.getStringExtra("STYLE");
        handleMostSimilarStyle();

        BeerStylesScraper scraper = new BeerStylesScraper();
        scraper.setOnBeerStylesFetchedListener(this);
        scraper.beerStyleScraper();


    }

    @Override
    public void onBeerStylesFetched(ArrayList<String> beerStyles) {
        mostSimilarStyle = SimilarityFinder.findSimilarStyle(style, beerStyles);

        if (mostSimilarStyle != null) {
            System.out.println("Similar style to " + style + ": " + mostSimilarStyle);
        } else {
            System.out.println("No similar style found for " + style + ".");
        }
        System.out.println("The most similar style to '" + "Belgian Strong Ale" + "' is: " + mostSimilarStyle);

    }

    public String parseString(String styleOrigin){
        String styleCorrect = "";

        styleCorrect = styleOrigin.toLowerCase().replace(" ", "-");;

        return styleCorrect;
    }

    private void handleMostSimilarStyle() {
        if(mostSimilarStyle != null) {
            webView.loadUrl("https://www.craftbeer.com/styles/" + parseString(mostSimilarStyle));
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webView.setWebViewClient(new WebViewClient());

        } else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    handleMostSimilarStyle(); // Call the method again
                }
            }, 500); // Wait for 1 second before checking again
        }
    }
}





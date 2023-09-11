package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    Button fetchData;
    RecyclerView myRecyclerView;
    String style;
    EditText numOfBeer;

    public ArrayList<BeerDescriptionModel> beerArrayCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData = findViewById(R.id.getInfoApi);
        myRecyclerView = findViewById(R.id.mRecyclerView);
        numOfBeer = findViewById(R.id.editTextNumber);

         // Start fetching data

        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(numOfBeer.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Input number", Toast.LENGTH_SHORT).show();
                } else{
                    ManageBeers manageBeers = new ManageBeers(MainActivity.this, Integer.parseInt(numOfBeer.getText().toString().trim()));
                    // Instantiate the RequestQueue.
                    String url = "https://random-data-api.com/api/v2/beers";
                    manageBeers.getArrayOfObjects(url, new ManageBeers.BeerListListener() {

                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onResponse(ArrayList<BeerDescriptionModel> beerArray) {
                            //Set ArrayAdapter
                            beerArrayCopy = beerArray;
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, beerArray, MainActivity.this);
                            myRecyclerView.setAdapter(adapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        style = beerArrayCopy.get(position).getStyle();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("STYLE", style);
        startActivity(intent);
    }
}

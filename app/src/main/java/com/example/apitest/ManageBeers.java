package com.example.apitest;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageBeers {
    private final Context context;
    private final int numOfBeers;

    public ManageBeers(Context context, int numOfBeers) {
        this.context = context;
        this.numOfBeers = numOfBeers;
    }


    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String jsonArray);

    }

    public interface BeerListListener {
        void onError(String message);

        void onResponse(ArrayList<BeerDescriptionModel> beerArray);

    }



    public void getArrayOfObjects(String url,  BeerListListener beerListListener){

        // Create a JSONArray to store the JSON objects
        final JSONArray jsonArray = new JSONArray();

        ArrayList<BeerDescriptionModel> beerDescriptionModelArrayList = new ArrayList<BeerDescriptionModel>();

        // Define the number of objects you want to fetch
         // Change this as needed

        for (int i = 0; i < numOfBeers; i++) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Add the received JSONObject to the JSONArray
                                jsonArray.put(response);

                                BeerDescriptionModel beerDescriptionModel = new BeerDescriptionModel();
                                beerDescriptionModel.setBrand(response.getString("brand"));
                                beerDescriptionModel.setName(response.getString("name"));
                                beerDescriptionModel.setStyle(response.getString("style"));
                                beerDescriptionModel.setAlcohol(response.getString("alcohol"));

                                beerDescriptionModelArrayList.add(beerDescriptionModel);

//                                Toast.makeText(context, beerDescriptionModel.getBeerdescription(), Toast.LENGTH_SHORT).show();
//                                volleyResponseListener.onResponse(jsonArray.toString());
//                                Toast.makeText(context, beer, Toast.LENGTH_SHORT).show();

                                // Check if you have collected enough objects
                                if (jsonArray.length() == numOfBeers) {
                                    // Optionally, you can display the JSONArray when you're done fetching data
//                                    Toast.makeText(context, beerDescriptionModelArrayList.toString(), Toast.LENGTH_SHORT).show();
                                    beerListListener.onResponse(beerDescriptionModelArrayList);
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            volleyResponseListener.onError(error.toString());
                            // Handle error
                        }
                    });

            // Add the request to the RequestQueue
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }
    }
}

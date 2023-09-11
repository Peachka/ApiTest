## Beer style discovery app

In this app is used Volley to get JSONObject from API

```gradle
implementation 'com.android.volley:volley:1.2.1'
```
Code to use Volley, better manage requests using **Singletone**, remember to add request to RequestQueue. The RequestQueue handles the request in the background and notifies your app when a response is received.
```java
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

String url = "https://random-data-api.com/api/v2/beers";
JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null)

addToRequestQueue(jsonObjectRequest);

```
You can see the response in RecyclerView, managing amount of JSONObject requests.

To discover beer styles user can click on beer item, and second activity will created with description of the same style or most similar style on the site. To get styles of beer we use WebScrapping and then modify url depending on the style.

```java
 Document doc = Jsoup.connect("https://www.craftbeer.com/beer-styles").get();
                    Elements styleWrappers = doc.select(".style-wrapper");
                     beerStyles = new ArrayList<>();

                    // Iterate through style wrappers and extract information
                    for (Element styleWrapper : styleWrappers) {
                        String styleName = styleWrapper.select(".caption-title").text();
                        beerStyles.add(styleName);

// Load webView with most similar style for ex. "chocolate-beer"
webView.loadUrl("https://www.craftbeer.com/styles/" + parseString(mostSimilarStyle));
```

The interface is simple to interact with. If you want to read about Vegetable beer and unfotlunately description is absent it will provade you with a similar one, for example Chocolate beer.

![api_app](https://github.com/Peachka/ApiTest/assets/76593453/9b695ae5-cbc5-4c7f-9c79-169608bb57a3)


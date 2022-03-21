package com.luba.picsumapplication.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.luba.picsumapplication.models.Picture;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PicsumRestClient {

    private static String url = "https://picsum.photos/v2/list?page=1&limit=1000";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getPictures(final PicsumCallback callback) {

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("LUBA", "onSuccess");
                System.out.println("Received event with data: " + response);
                JSONArray pictureJsonResults = response;
                ArrayList<Picture> pictures = new ArrayList<>();

                try {
                    for (int i = 0; i < pictureJsonResults.length(); i++) {

                        pictures.add(new Picture(pictureJsonResults.getJSONObject(i)));
                    }
                    callback.onSuccess(pictures);

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(new Error(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("LUBA", "onFailure");
                super.onFailure(statusCode, headers, responseString, throwable);
                callback.onError(new Error(throwable.getMessage()));
            }
        });

    }

}

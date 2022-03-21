package com.luba.picsumapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Picture implements Serializable {
    String author ="";
    String download_url = "";

    public Picture(JSONObject jsonObject) throws JSONException {
        this.author = jsonObject.getString("author");
        this.download_url = jsonObject.getString("download_url");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}

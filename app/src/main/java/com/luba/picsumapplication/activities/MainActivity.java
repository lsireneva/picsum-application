package com.luba.picsumapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.luba.picsumapplication.R;
import com.luba.picsumapplication.adapters.PicsumRecyclerViewAdapter;
import com.luba.picsumapplication.adapters.SelectListener;
import com.luba.picsumapplication.models.Picture;
import com.luba.picsumapplication.network.PicsumCallback;
import com.luba.picsumapplication.network.PicsumRestClient;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {
    ArrayList<Picture> pictures;
    PicsumRecyclerViewAdapter adapter;
    RecyclerView rvAuthors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAuthors = (RecyclerView) findViewById(R.id.rv_authors);
        rvAuthors.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvAuthors.setLayoutManager(linearLayoutManager);
        adapter = new PicsumRecyclerViewAdapter(pictures, this,  this);
        rvAuthors.setAdapter(adapter);

        PicsumRestClient.getPictures(new PicsumCallback() {
            @Override
            public void onSuccess(ArrayList<Picture> pictures) {
                Log.d("LUBA", "MainActivity onSuccess");
                MainActivity.this.pictures = new ArrayList<>();
                MainActivity.this.pictures.addAll(pictures);
                adapter.notifyDataSetChanged(MainActivity.this.pictures);
            }

            @Override
            public void onError(Error error) {
                Log.d("LUBA", "MainActivity onError"+error);
                error.printStackTrace();
            }
        });
    }

    @Override
    public void OnPictureAuthorClicked(Picture pictures) {
        startActivity(new Intent(MainActivity.this, PictureDetailActivity.class)
        .putExtra("data", pictures));
    }
}
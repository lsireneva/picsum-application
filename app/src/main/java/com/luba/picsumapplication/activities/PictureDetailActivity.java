package com.luba.picsumapplication.activities;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luba.picsumapplication.R;
import com.luba.picsumapplication.models.Picture;


public class PictureDetailActivity extends AppCompatActivity {

    private Picture picture;
    ImageView ivPicture;
    TextView tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_details);
        tvAuthor = (TextView) findViewById(R.id.tv_picture_author);
        ivPicture = (ImageView) findViewById(R.id.iv_picture);
        picture = (Picture) getIntent().getSerializableExtra("data");
        tvAuthor.setText(picture.getAuthor());

        Glide
                .with(this)
                .load(picture.getDownload_url())
                //.override(600,600)
                .centerCrop()
                //.fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(ivPicture);
    }
}

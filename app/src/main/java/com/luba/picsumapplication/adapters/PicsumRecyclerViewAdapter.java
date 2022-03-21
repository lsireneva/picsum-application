package com.luba.picsumapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.luba.picsumapplication.R;
import com.luba.picsumapplication.models.Picture;

import java.util.ArrayList;

public class PicsumRecyclerViewAdapter extends RecyclerView.Adapter<PicsumRecyclerViewAdapter.PicsumViewHolder> {
    private ArrayList<Picture> pictures;
    private Context mContext;
    private SelectListener listener;


    public PicsumRecyclerViewAdapter(ArrayList<Picture> pictures, Context mContext, SelectListener listener) {
        this.pictures = pictures;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PicsumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PicsumViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PicsumViewHolder holder, int position) {
        holder.author.setText(pictures.get(position).getAuthor());
        holder.author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnPictureAuthorClicked(pictures.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.pictures != null ? this.pictures.size() : 0;
    }

    public void notifyDataSetChanged(ArrayList<Picture> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }


    public class PicsumViewHolder extends RecyclerView.ViewHolder {
        TextView author;

        public PicsumViewHolder(@NonNull View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.tv_author);

        }
    }

}


package com.example.aliothman.mov;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adaptor extends BaseAdapter {

    List<Movie> movieList;
    Context context ;
    Uri uri;

    public Adaptor(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = movieList.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.item_movie ,parent ,false);
        ImageView imageView ;
        TextView tittleTV , DateTV;
        ImageButton Love;
        imageView = convertView.findViewById(R.id.movie_image);
            tittleTV = convertView.findViewById(R.id.movie_tittle);
            DateTV = convertView.findViewById(R.id.movie_Date);

       imageView.setImageURI(uri.parse(movie.getImg()));
            tittleTV.setText(movie.getTittle());
            DateTV.setText(movie.getDate());
            Love = convertView.findViewById(R.id.Add_To_favourit);
            Love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Done to favourite", Toast.LENGTH_SHORT).show();
                }
            });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                    dataBaseHelper.deleteProduct(movie.getId());
                    ((Activity)context).finish();
                    context.startActivity(((Activity) context).getIntent());
                    return true;
                }
            });
        return convertView;
    }
}

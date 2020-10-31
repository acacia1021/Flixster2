package com.example.flixster2.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster2.R;
import com.example.flixster2.models.DetailActivity;
import com.example.flixster2.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    //this is a constructor for the two above
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //Get the movie at the position
        Movie movie = movies.get(position);
        //Bind the movie data int the ViewHolder
        holder.bind(movie);
    }

    //returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            //Android doesn't have an in built way to render remote images, so we use a library for that, using the Glide Resource that can be found on the Assignment page
Glide.with(context).load(movie.getPosterPath()).into(ivPoster);


            //1. Register the click listener on the whole table
            container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2. Navigate to a new activity on tap
                Intent i = new Intent(context, DetailActivity.class);
                //i.putExtra("title", movie.getTitle());
                //If the model class is Parcelable, then Android Studio will know how to break the object down and reconstruct it on the receiving activity
                i.putExtra("movie", Parcels.wrap(movie));
                context.startActivity(i);
                //This line of code creates a toast of movie title on tap
              //  Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
            }

        }
    }


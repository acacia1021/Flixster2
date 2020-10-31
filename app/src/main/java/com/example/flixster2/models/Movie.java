package com.example.flixster2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel

public class Movie {

    int movieId;
    String posterPath;
    String title;
    String overview;
    double rating;
    double popularity;

    //don't forget to add your empty constructor for parceler!!
    //empty constructor needed by the Parceler Library
    public Movie(){

    }

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        popularity = jsonObject.getDouble("popularity");
        movieId = jsonObject.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        //putting %s where the original URL info was basically just points it to the second parameter which is posterpath as shown in the next line
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating(){
        return rating;
    }

    public String getPopularity(){
        return ("Popularity: " + popularity);
    }

    public int getMovieId() {
        return movieId;
    }
}


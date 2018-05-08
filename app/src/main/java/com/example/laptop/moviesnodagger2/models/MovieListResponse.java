package com.example.laptop.moviesnodagger2.models;

import com.example.laptop.moviesnodagger2.models.MovieListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {

    @SerializedName("results")
    @Expose
    private List<MovieListResult> results = null;

    public List<MovieListResult> getResults() {
        return results;
    }
}

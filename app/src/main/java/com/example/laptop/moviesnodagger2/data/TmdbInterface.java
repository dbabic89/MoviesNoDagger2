package com.example.laptop.moviesnodagger2.data;

import com.example.laptop.moviesnodagger2.models.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbInterface {

    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

}

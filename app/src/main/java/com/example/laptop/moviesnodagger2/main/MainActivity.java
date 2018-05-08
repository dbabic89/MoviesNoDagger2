package com.example.laptop.moviesnodagger2.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.laptop.moviesnodagger2.BuildConfig;
import com.example.laptop.moviesnodagger2.MoviesNoDagger2;
import com.example.laptop.moviesnodagger2.R;
import com.example.laptop.moviesnodagger2.data.TmdbInterface;
import com.example.laptop.moviesnodagger2.models.MovieListResponse;
import com.example.laptop.moviesnodagger2.models.MovieListResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter moviesAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesAdapter = new MoviesAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_movies);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moviesAdapter);

        TmdbInterface tmdbInterface = MoviesNoDagger2.getMoviesNoDagger2(this).getTmdbInterface();
        Call<MovieListResponse> call = tmdbInterface.getPopularMovies(BuildConfig.TMDB_APIKEY, "en-US", 1);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                if (response.body() != null) {
                    List<MovieListResult> movies = response.body().getResults();
                    moviesAdapter.addAll(movies);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                Log.e("TAG", t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}

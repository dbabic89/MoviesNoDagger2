package com.example.laptop.moviesnodagger2;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.laptop.moviesnodagger2.data.TmdbInterface;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNoDagger2 extends Application {

    private TmdbInterface tmdbInterface;

    public static MoviesNoDagger2 getMoviesNoDagger2(Activity activity) {
        return (MoviesNoDagger2) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.i("TAG", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        File file = new File(this.getCacheDir(), "okhttp_cache");

        Cache cache = new Cache(file, 10 * 1000 * 1000);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        tmdbInterface = retrofit.create(TmdbInterface.class);
    }

    public TmdbInterface getTmdbInterface() {
        return tmdbInterface;
    }
}

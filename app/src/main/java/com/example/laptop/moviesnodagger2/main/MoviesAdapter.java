package com.example.laptop.moviesnodagger2.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptop.moviesnodagger2.R;
import com.example.laptop.moviesnodagger2.models.MovieListResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieListResult> listItems;
    private Context context;

    MoviesAdapter(Context context) {
        this.context = context;
        listItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return getViewHolder(parent, layoutInflater);
    }

    @NonNull
    private MoviesAdapter.MovieViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View v1 = inflater.inflate(R.layout.item_movie_list, parent, false);
        return new MoviesAdapter.MovieViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        MovieListResult listItem = listItems.get(position);

        String titleAndDate = listItem.getTitle() + " (" + listItem.getReleaseDate().substring(0, 4) + ")";
        holder.title.setText(titleAndDate);
        holder.description.setText(listItem.getOverview());
        holder.position.setText(String.valueOf(position + 1));
        holder.tmdbRating.setText(String.valueOf(listItem.getVoteAverage()));

        Picasso.with(context).load("https://image.tmdb.org/t/p/w342" + listItem.getPosterPath()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    private void add(MovieListResult item) {
        listItems.add(item);
        notifyItemInserted(listItems.size() - 1);
    }

    void addAll(List<MovieListResult> items) {
        for (MovieListResult item : items) {
            add(item);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView position;
        TextView title;
        TextView description;
        TextView tmdbRating;

        MovieViewHolder(View v) {
            super(v);
            poster = v.findViewById(R.id.image_poster);
            position = v.findViewById(R.id.text_position);
            title = v.findViewById(R.id.text_title);
            description = v.findViewById(R.id.text_description);
            tmdbRating = v.findViewById(R.id.text_tmdb_rating);
        }
    }
}
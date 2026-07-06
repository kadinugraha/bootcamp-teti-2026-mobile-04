package com.example.movieinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.moviePoster)
        val title: TextView = view.findViewById(R.id.movieTitle)
        val overview: TextView = view.findViewById(R.id.movieOverview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.overview.text = movie.overview

        // TMDB image base URL is required to render poster paths
        val imageUrl = "https://image.tmdb.org/t/p/original"+movie.posterPath

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.poster)
    }

    override fun getItemCount(): Int = movies.size
}

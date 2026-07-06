package com.example.movieinfo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val API_KEY = "7b16bf52e21d7f9f2d963c61b662ec62" // Replace with your actual key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //add this code
        recyclerView = findViewById(R.id.movieRecyclerView)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(TMDBApiService::class.java) // Use your TMDBApiService interface

        lifecycleScope.launch {
            try {
                val response = apiService.getPopularMovies(API_KEY)
                Log.d("myresponse",response.toString())
                val adapter = MovieAdapter(response.results)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching movies", e)
            }
        }
    }
}
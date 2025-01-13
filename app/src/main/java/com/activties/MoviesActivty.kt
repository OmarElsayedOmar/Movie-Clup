package com.activties

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adapters.CustomAdapter
import com.data.MovieList
import com.data.MovieResponse
import com.data.RetrofitClient
import com.example.myapplication.databinding.ActivityMoviesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesActivty : AppCompatActivity(),MovieInteractionListener {
    lateinit var binding: ActivityMoviesBinding
    private lateinit var newRecyclerView: RecyclerView
    private val MovieList = mutableListOf<MovieList>()
    private lateinit var movieAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newRecyclerView = binding.RecyclerView1
        newRecyclerView.setHasFixedSize(true)
        loadMovies()
    }

    private fun loadMovies() {
            val apiKey = "ed0579c5972d5b789fec9a33235fcf3f"
            RetrofitClient.apiService.getPopularMovies(apiKey)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.results?.let { movies ->
                                MovieList.clear()
                                MovieList.addAll(movies )
                               // Log.d("Movies Loaded", "Movie List: $movieList")
                                movieAdapter = CustomAdapter(MovieList,this@MoviesActivty)
                                newRecyclerView.adapter = movieAdapter
                            }
                        }  else {
                        Toast.makeText(this@MoviesActivty, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Toast.makeText(this@MoviesActivty, "Failed to load movies", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    override fun onClickItem(movieList: MovieList) {
        val intent= Intent(this,DetailsActivity::class.java)
        intent.putExtra("movie",movieList)
        startActivity(intent)
    }
}

package com.activties
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adapters.CustomAdapter
import com.data.MovieList
import com.data.RetrofitClient
import com.example.myapplication.databinding.ActivityMoviesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getPopularMovies(apiKey)

                withContext(Main) {
                    response.results.let { movies ->
                        MovieList.clear()
                        MovieList.addAll(movies)
                        movieAdapter = CustomAdapter(MovieList, this@MoviesActivty)
                        newRecyclerView.adapter = movieAdapter
                    }
                }
            } catch (e: Exception) {
                withContext(Main) {
                    Toast.makeText(this@MoviesActivty, "Failed to load movies: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onClickItem(movieList: MovieList) {
        val intent= Intent(this,DetailsActivity::class.java)
        intent.putExtra("movie",movieList)
        startActivity(intent)
    }
}

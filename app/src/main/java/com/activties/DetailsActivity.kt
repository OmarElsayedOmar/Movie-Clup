package com.activties
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adapters.CastAdapter
import com.data.MovieList
import com.bumptech.glide.Glide
import com.data.CastList
import com.data.CreditsResponse
import com.data.RetrofitClient
import com.example.myapplication.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    private lateinit var castRecyclerView: RecyclerView
    private val CastList = mutableListOf<CastList>()
    private lateinit var castAdapter: CastAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        castRecyclerView = binding.RecyclerView2
        val movie = intent.getSerializableExtra("movie") as MovieList
        binding.movieTitle.text = movie.title
        binding.movieOverview.text = movie.overview
        binding.movieReleaseDate.text = "Release Date: ${movie.release_date}"
        binding.movieRating.text = "vote_average: ${movie.vote_average}"
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}")
            .into(binding.moviePoster)
        loadMoviesCast(movie.id)
    }

    private fun loadMoviesCast(movieId: Int) {
        val apiKey = "ed0579c5972d5b789fec9a33235fcf3f"
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(IO).launch {
            try {
                val response = RetrofitClient.apiService.getMoviesCredits(movieId, apiKey)
                withContext(Main) {
                    CastList.clear()
                    CastList.addAll(response.cast)
                    castAdapter = CastAdapter(CastList, this@DetailsActivity)
                    castRecyclerView.adapter = castAdapter
                    binding.progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Main) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@DetailsActivity, "Failed to load Cast: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}




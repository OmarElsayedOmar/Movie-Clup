package com.activties
import android.os.Bundle
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
     RetrofitClient.apiService.getMoviesCredits(movieId,apiKey)
         .enqueue(object : Callback<CreditsResponse> {
                override fun onResponse(
                    call: Call<CreditsResponse>,
                    response: Response<CreditsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.cast?.let { credits->
                            CastList.clear()
                            CastList.addAll(credits)
                            castAdapter = CastAdapter(CastList,this@DetailsActivity)
                            castRecyclerView.adapter = castAdapter
                        }
                    }  else {
                        Toast.makeText(this@DetailsActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                    Toast.makeText(this@DetailsActivity, "Failed to load Cast", Toast.LENGTH_SHORT).show()
                }

            })
    }


    }





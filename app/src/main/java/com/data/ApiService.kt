package com.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
   suspend fun getPopularMovies(@Query("api_key") apiKey: String):MovieResponse
    @GET("movie/{movie_id}/credits")
   suspend fun getMoviesCredits(@Path("movie_id")movieId:Int,@Query("api_key") apiKey: String):CreditsResponse
}

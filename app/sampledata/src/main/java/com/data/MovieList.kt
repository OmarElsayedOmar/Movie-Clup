    package com.data
    import java.io.Serializable
    data class MovieList(
        val title: String,
        val overview: String,
        val poster_path: String,
        val id: Int,
        val vote_average: Double,
        val backdrop_path:String,
        val release_date :String
    ): Serializable


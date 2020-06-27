package com.spexco.moviedbexample.remote

import com.spexco.moviedbexample.model.FilmResponse
import com.spexco.moviedbexample.model.GenreResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/genre/movie/list?api_key=3bb3e67969473d0cb4a48a0dd61af747&language=en-US")
    fun getGenres(): Single<GenreResponse>

    @GET("3/discover/movie?api_key=3bb3e67969473d0cb4a48a0dd61af747&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getFilms(@Query("with_genres") id: Long): Single<FilmResponse>
}
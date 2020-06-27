package com.spexco.moviedbexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class FilmResponse(
    val page: Long,
    val total_results: Long,
    val total_pages: Long,
    val results: List<FilmResult>?
)

@Parcelize
data class FilmResult(
    //val popularity: Double,
    val vote_count: Long,
    //val video: Boolean,
    val poster_path: String,
    val id:Long,
    //val adult:Boolean,
    val backdrop_path:String,
    val original_language:String,
    val original_title:String,
    //val genre_ids:List<Long>?,
    val title:String,
    val vote_average:Double,
    val overview:String,
    val release_date:String
): Parcelable



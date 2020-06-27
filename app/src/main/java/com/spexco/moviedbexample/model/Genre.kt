package com.spexco.moviedbexample.model

data class GenreResponse(
   val genres:List<Genre>?
)


data class Genre(
    val id:Long,
    val name:String
)